# Spring Cloud Gateway + Resource Server + Webflux , multiple instance test on PCF

## Build and Deploy the Gateway

### From the gateway directory :

```sh
gradle build
cf push my-gateway -i 2 -p build/libs/gateway-1.0-SNAPSHOT.jar
cf set-env my-gateway uaa-url MY-UAA-URL
cf set-env my-gateway uaa-client-id MY-UAA-CLIENT-ID
cf set-env my-gateway uaa-client-secret MY-UAA-CLIENT-SECRET
cf vic-gateway restage
```

Note: replace MY-UAA-URL with the URL for your UAA Provider for example : https://myuaa.login.run.pivotal.io

Note: replace MY-UAA-CLIENT-ID and MY-UAA-CLIENT-SECRET with your generated UAA Client ID and Secret

Note: in the application.yml update *resource-server-url* and *redirect-uri-template* if needed

## Build and Deploy the Resource Server

### From the resource server directory :

```sh
gradle build
cf push vic-resource-server -i 2 -p build/libs/resource-server-1.0-SNAPSHOT.jar 
cf set-env vic-resource-server uaa-url MY-UAA-URL
cf vic-gateway restage
```

Note: replace MY-UAA-URL with the URL for your UAA Provider for example : https://myuaa.login.run.pivotal.io
