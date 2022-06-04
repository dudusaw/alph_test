## Launch instructions (docker)

- Clone repository `git clone https://github.com/dudusaw/alph_test`
- Change dir `cd ./alph_test`
- Build jar `./gradlew bootJar`
- Build image `docker build -t test-app .`
- Create and run the container 
```
docker run -p 8085:8080 \
-e APP_ID=088dc375bd494623adb6a6b628dc535e \
-e GIPHY_API_KEY=8bOZd9RlyJe9PM0mMPJDLrAc1dFUpHNP \
test-app
```
- Make a request [localhost:8085/currency/compare?code=AMD](http://localhost:8085/currency/compare?code=AMD)
