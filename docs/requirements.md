# Assignment plan

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
