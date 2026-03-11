# SOFE3290_Lab3Part2_100792709_CRN75766

## Introduction  
---
In this lab, a Continuous Integration/Continuous Deployment (CI/CD) pipeline was created for a Binary Calculator web app. Continuous Integration focuses on automatically building and testing code whenever changes are made, which finds errors early and makes sure that that new updates work properly. Continuous Deployment then automatically deploys the application after a successful test pass.   
The pipeline was deployed using Jenkins, which connects to the project repository, builds the application using Maven, creates a container image using Google Cloud Build, and deploys the application to a Kubernetes cluster running on Google Cloud Shell.  
The labs tasks were to understand how automated pipelines work and how containerized applications can be deployed in a cloud environment. The Binary Calculator web-application was successfully built, tested, containerized, serviced, and deployed through the Jenkins pipeline.  

## Discussion  
---
**What do pipeline, node, agent, stage, and steps mean in the context of Jenkins?**  

A *pipeline* is the workflow of the CI/CD process, and has all the steps Jenkins should do, such as retrieving code, running tests, building the application, and deploying it.   
A *node* is a machine where Jenkins runs tasks, like the Jenkins server itself or another connected machine that performs builds. Nodes let Jenkins distribute tasks over different systems if necessary.  
An *agent* is the environment where the pipeline or a stage runs; where the tasks will execute, such as a node or inside a container. Agents are used to run builds in isolated environments for most pipelines.  
A *stage* is ann important step in the pipeline process. Each stage groups related tasks together. For example, in Jenkinsfile_v2: checkout, testing, building, containerizing, and deployment. Stages help organize the pipeline and what is happening during execution.  
Finally, *steps* are the individual commands that Jenkins executes inside a stage. These commands perform the actual work, such as running Maven commands, executing shell scripts, or deploying containers to Kubernetes.  


## Design   
---
The CI/CD pipeline was designed using Jenkins pipelines with a Jenkinsfile stored in the GitHub repository. The pipeline structure is made up of 5 or so automated stages that execute one after the other to build and deploy the application. The system is made of these sections/parts:  
1. **GitHub Repository:** store binarycalculatorwebapp source code and the jenkinsfile and values.yaml file for Jenkins deployment and builds.  
2. **Jenkins:** automate the CI/CD pipeline and runs all stages in Jenkinsfile_v2 (34.29.118.210)  
3. **Maven:** build and compile Java application and run build and unit tests   
4. **Google Cloud Build:** builds the Dockerfile image and containerize it  
5. **Docker Container:** packages the application and its dependencies.  
6. **Kubernetes (GKE):**  deploys and manages the deployment and execution of the containerized binary calculator web-application  
7. **LoadBalancer Service:** exposes the application to external users through a public/external IP address (34.44.125.46).  

### Stages of Jenkins Pipeline  
1. **Source Code Checkout:** Jenkins takes the latest version of the application code from GitHub repo, so the pipeline always builds and tests the most recent code.  
2. **Automated Testing:** Maven is used to run automated tests on the application, with command: mvn clean test. Compile application and execute unit test to verify web-app works correctly. If tests fail, the pipeline stops and prevents deployment.  
3. **Containerization:** After test success, the web-app is containerized using Google Cloud Build. The pipeline builds a Docker image with a web-app and pushes to the container registry.  
4. **Deployment:** The pipeline then deploys the containerized application to the Kubernetes cluster using the command: kubectl create deployment binarycalculator-deployment  
5. **Service Exposure:** A Kubernetes LoadBalancer service is created to expose the application externally. This allows users to access the Binary Calculator through a public IP address. Can check with kubectl get pods or kubectl get svc.  

### Video Links  
---
**Design Video:** https://www.youtube.com/watch?v=jcdhfCI7UgA  
**CI/CD Video:** https://www.youtube.com/watch?v=Nw74bH42gm0
