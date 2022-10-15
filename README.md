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

### PostgreSQL
    컨테이너실행: docker run --name db -p 5432:5432 --network=db -v "$PWD:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=1234 -d postgres:alpine
    컨테이너접속: docker run -it --rm --network=db postgres:alpine psql -h db -U postgres

### AWS ElastickBeansTalk 배포
    aws-elasticbeanstalk/docker-compose.yml 
    