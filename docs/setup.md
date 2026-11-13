# Run and demonstrate the project

## Tools

Install Git, Java 17 or newer, Maven, Jenkins, Ansible, Docker Engine, Kind and kubectl. The demonstration uses Ubuntu under WSL with systemd. Jenkins itself runs on Java 21; Maven compiles this application for Java 17.

## Application and tests

```bash
git clone https://github.com/05Shaibaan/Devops-Project.git
cd Devops-Project
mvn clean package
java -jar target/unit-converter.jar
```

In a second terminal:

```bash
curl 'http://localhost:8080/temperature?celsius=25'
curl 'http://localhost:8080/distance?kilometres=5'
curl 'http://localhost:8080/health'
curl -i 'http://localhost:8080/distance?kilometres=-1'
```

The last request must return HTTP 400. Run `mvn test` for all 15 tests. The test suite covers ordinary conversions, negative temperature, zero distance, missing parameters, malformed numbers, non-finite numbers, negative distance and the input limit.

## Jenkins

1. Install the Pipeline, Git, JUnit and Timestamper plugins. Ensure Java and `mvn` are available to the Jenkins agent.
2. Create a Pipeline job named `unit-converter`.
3. Choose **Pipeline script from SCM**, Git, repository `https://github.com/05Shaibaan/Devops-Project.git`, branch `*/main`, script path `Jenkinsfile`.
4. Run **Build Now** once to load the pipeline and its polling trigger.
5. Confirm Test and Build succeed, 15 tests pass, and `target/unit-converter.jar` is archived.
6. Push a small real change to `main`. The pipeline polls every minute using `* * * * *`. The next build should say **Started by an SCM change**.

`mvn -B clean test` executes the tests first. `mvn -B -DskipTests package` then packages the already-tested classes. Packaging is never reached after a failing Test stage. JUnit publishes the test report, and archive fingerprinting identifies the exact JAR.

## Ansible

The included inventory targets the local Ubuntu host. Run from Linux/WSL, with privilege escalation available:

```bash
cd ansible
ansible-playbook -i inventory playbook.yml --ask-become-pass
ansible-playbook -i inventory playbook.yml --ask-become-pass
cd ..
curl 'http://localhost:8093/temperature?celsius=25'
```

If running as root, omit `--ask-become-pass`. For a remote Ubuntu server, replace the inventory's local connection with its SSH host settings. The health check runs on the target host.

The `converter` role was initialized with `ansible-galaxy role init converter`. It installs Java, creates a service user, copies the JAR, installs and starts `unit-converter.service`, and checks `/health`. Change `app_port` in the role defaults to choose another port. A handler restarts the service when its JAR or unit file changes. A second unchanged run should report `changed=0` and `failed=0`.

## Docker

```bash
mvn clean package
docker build -t unit-converter:1.0 .
docker images unit-converter:1.0
docker run -d --name unit-converter -p 127.0.0.1:8095:8080 unit-converter:1.0
docker ps --filter name=unit-converter
curl 'http://localhost:8095/distance?kilometres=5'
docker rm -f unit-converter
```

The image contains the runtime and executable JAR; it runs as user 10001. `.dockerignore` sends only the JAR to the image build.

## Kubernetes

```bash
kind create cluster --name converter-demo
bash scripts/deploy.sh
kubectl --context kind-converter-demo get nodes
kubectl --context kind-converter-demo -n converter-demo get pods
kubectl --context kind-converter-demo -n converter-demo get svc
kubectl --context kind-converter-demo -n converter-demo port-forward service/unit-converter 8096:8080
```

In another terminal:

```bash
curl 'http://localhost:8096/temperature?celsius=25'
curl 'http://localhost:8096/distance?kilometres=5'
bash scripts/scale-demo.sh
kubectl --context kind-converter-demo -n converter-demo get configmap unit-converter-config
kubectl --context kind-converter-demo -n converter-demo exec deployment/unit-converter -- printenv APP_PORT
```

The Service selector matches `app: unit-converter` on the pods. The scaling script waits for each 3 -> 5 -> 3 rollout. The ConfigMap provides `APP_PORT=8080`; the startup, readiness and liveness probes use `/health`. Keep this port at 8080 unless the container port and probes are updated as well. After changing ConfigMap values, restart the Deployment to refresh environment variables.

To demonstrate again, keep Docker and Jenkins running, start the Ansible service if needed, and rerun the scaling script. To remove this project's resources, use `kind delete cluster --name converter-demo` and `sudo systemctl stop unit-converter`. These commands target this project's demo resources.

## Submission

Include the source repository, report PDF and `repo_link.txt`. The report keeps the supplied certificate and assessment tables, with blank signature and evaluator-mark areas. Its course code follows the assignment brief, `25CYAEC59`; the supplied template used `24CYAEC59`, so confirm the applicable code with the course coordinator. Add the coordinator as a repository collaborator once their GitHub username is supplied.
