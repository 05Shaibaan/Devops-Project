# Execution evidence

These records belong to the Unit Converter API in this repository. They were captured from its own build, Ansible service, Docker image and Kubernetes deployment.

Screenshots 01-05 and 07-08 show GitHub and Jenkins pages. Screenshot 06 shows a labelled excerpt of the actual Jenkins console output. Screenshots 09-21 show browser views of the actual recorded command output; they are labelled as recorded output, rather than terminal windows. Full logs are retained in `logs/`. The Ansible screenshot highlights its handler and both PLAY RECAP results; its full log also contains the initial successful startup retry.

| No. | Evidence |
| --- | --- |
| 01 | [Historical 13 November branch capture](01-github-branches.jpg) |
| 02 | [Archived 13 November merge history, before the PR rebuild](02-github-merge-history.jpg) |
| 03 | [Jenkins repository configuration](03-jenkins-repository-configuration.jpg) |
| 04 | [Jenkins SCM polling](04-jenkins-scm-trigger.jpg) |
| 05 | [Successful pipeline](05-jenkins-successful-pipeline.jpg) |
| 06 | [Test and Build console output](06-jenkins-test-and-build-console.jpg) |
| 07 | [Archived JAR](07-jenkins-archived-jar.jpg) |
| 08 | [Passing automated tests](08-jenkins-passing-tests.jpg) |
| 09 | [Ansible inventory and playbook](09-ansible-inventory-playbook.jpg) |
| 10 | [Ansible execution and repeat run](10-ansible-play-recap.jpg) |
| 11 | [Dockerfile](11-dockerfile.jpg) |
| 12 | [Docker build](12-docker-image-build.jpg) |
| 13 | [Docker image listing](13-docker-images.jpg) |
| 14 | [Running container and conversion](14-running-docker-container.jpg) |
| 15 | [Ready Kind node](15-kind-node-ready.jpg) |
| 16 | [Three replicas](16-kubernetes-three-replicas.jpg) |
| 17 | [Kubernetes Service](17-kubernetes-service.jpg) |
| 18 | [API through the Service](18-application-through-service.jpg) |
| 19 | [Five replicas](19-kubernetes-five-replicas.jpg) |
| 20 | [Scaled down to three](20-kubernetes-scaled-down.jpg) |
| 21 | [ConfigMap and health probes](21-configmap-settings.jpg) |

The cluster is Kind `converter-demo`, the namespace is `converter-demo`, and the Deployment/Service is `unit-converter`. Kind is one of the local cluster options allowed by the assignment. These resource names follow the actual application.

[Recorded PR metadata](logs/pull-request-1.json), [current Git history](logs/git-pr-history.txt) and [merged PR #1](https://github.com/05Shaibaan/Devops-Project/pull/1) verify the actual GitHub merge on 16 November 2026 at 14:54:58 IST. Images 01-08 remain the original historical captures from 13 November; image 02 predates the PR rebuild and does not describe current main. The refreshed raw Jenkins logs record successful build #5 after PR #1. Deployment captures retain their original dates because the application and configuration are unchanged. The temporary remote backup branch was deleted at the owner's request; a local Git bundle retains the original history.
