### 구성
    openjdk 11
    react
    springboot
    
### 빌드
    docker build : ./mvnw clean install jib:dockerBuild -Dimage=spring-react-fullstack:v1
    docker push  : ./mvnw clean install jib:build -Djib.to.image=deneb1593/spring-react-fullstack:v1

    ./mvnw clean install -P build-frontend -P jib-push-to-dockerhub -Dapp.image.tag=2
    ./mvnw clean install -P build-frontend -P jib-push-to-local -Dapp.image.tag=latest

### 실행
    docker run --name fullstack -p 8080:8080 spring-react-fullstack:v1


### AWS ElastickBeansTalk 배포
    aws-elasticbeanstalk/docker-compose.yml 
    