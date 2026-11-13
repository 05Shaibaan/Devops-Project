# Assignment coverage

Build one stateless Unit Converter API with Java 17, Spring Boot and Maven. Carry the same executable JAR through both components.

1. Create a working API with three GET routes, input validation and automated tests.
2. Keep `main` and `feature/unit-conversions`, with a visible merge commit.
3. Configure Jenkins from the GitHub repository, poll SCM every minute, test before packaging, and archive the runnable JAR.
4. Configure an Ubuntu host through an Ansible inventory, role, variable and restart handler.
5. Build and run a Docker image as a non-root user.
6. Deploy three Kubernetes replicas and a Service, check HTTP responses, scale to five and return to three.
7. Use automated tests as the CI enhancement, and health probes plus a ConfigMap as deployment enhancements.
8. Capture this project's own execution evidence and prepare a report in the supplied college format.

Acceptance: correct conversion results, invalid requests return HTTP 400, Maven tests pass, executable JAR starts, Jenkins succeeds and archives the JAR, Ansible rerun has no changes, Docker responds, and Kubernetes finishes with three ready replicas.

The coordinator's collaborator invitation and handwritten signatures require the relevant person's details or action. Evaluator marks stay blank.

## Verified results

| Requirement | Files and result |
| --- | --- |
| Three-route API without a database | `ConversionController.java`; packaged JAR checked through HTTP |
| Maven artifact | `pom.xml`; `target/unit-converter.jar` built successfully |
| Feature branch and merge | `main`, `feature/unit-conversions`; merge `8ed7386` |
| GitHub identity | Published commits map to `05Shaibaan`; GitHub shows one contributor |
| Jenkins SCM, trigger, build and archive | `Jenkinsfile`; build #2 started by an SCM change, completed SUCCESS and archived the JAR |
| CI enhancement | 15 tests passed before packaging; JUnit result retained in `evidences/logs/jenkins-result.json` |
| Ansible host and runtime/service setup | `ansible/inventory`, `playbook.yml`, role `converter`; zero failed/unreachable hosts |
| Variable, handler and initialized role | Role defaults, restart handler and service template; second run `changed=0` |
| Containerization | Docker image `unit-converter:1.0` built and local container returned the conversion |
| Local cluster and Service | Kind `converter-demo`; ClusterIP Service `unit-converter`; HTTP requests succeeded |
| Scaling | Three, five and three ready pods recorded in screenshots/logs 16, 19 and 20 |
| Deployment enhancements | `k8s/configmap.yaml` supplied `APP_PORT=8080`; startup/readiness/liveness probes use `/health` |
| Report format and both components | Personalized PDF and editable LaTeX in `reports/`, including separate required sections, code, screenshots, conclusions, references and resume lines |
| Source code and repository link | Full source in this repository and link in report plus `repo_link.txt` |

The report course code is `25CYAEC59`, following the assignment brief. Confirm this with the coordinator because the supplied template says `24CYAEC59`. Coordinator collaborator access and handwritten signatures remain submission actions; no completed signature or awarded mark is claimed.
