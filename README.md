# chy.stat API java client

[![Build](https://github.com/diribet/chystat-api-client-java/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/diribet/chystat-api-client-java/actions/workflows/build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cz.diribet/chystat-api-client-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cz.diribet/chystat-api-client-java)

A Java client that you can use to work with the public [chy.stat](http://www.chystat.com) API.

### How to install the client with Maven

To install chy.stat API client with Maven2/3, you can use the library version available in Maven Central 
by adding the following dependency

```xml
<dependencies>
    <dependency>
        <groupId>cz.diribet</groupId>
        <artifactId>chystat-api-client-java</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### How to use the client

To use the client you have to provide 

- base URL
- JwtTokenProvider

and optionally an [Gson](https://github.com/google/gson) object

#### Base URL

It's a base URL. All request are then evaluated relative to this URL. For example 

`"https://some.chystat.site/api/v1"`

#### Gson

We use Gson to serialize a Java object to the request body and deserialize response body to a Java object respectively.

#### JwtTokenProvider

All requests to the [chy.stat](http://www.chystat.com) API are authenticated and authorized. 
We use [HMAC authentication](https://en.wikipedia.org/wiki/HMAC) and [JWT (JSON Web Tokens)](https://jwt.io)

You can use the default provider implementation which generates the JWT token for you. 
See [API](https://apidocs.chystat.com/2.0.0-SNAPSHOT) documentation where is described 
how to get the required token ID and the token secret.

### Code example

```java
String baseUrl = "https://some.chystat.site/api/v1"
String tokenId = "i54m56W5R2aJVw/JYq1wIQ"
String tokenSecret = "CFu5345NWMR2E4eP"
JwtTokenProvider jwtTokenProvider = new DefaultJwtTokenProvider(tokenId, tokenSecret);

IApiClient client = new ChystatApiClient(baseUrl, jwtTokenProvider);
ResponsePayload responsePayload = client.get("/orders/orders/1")
```

See the current [API](https://apidocs.chystat.com/2.0.0-SNAPSHOT) documentation for more details
about the API and authentication.

### Contributing

Please feel free to open a GitHub Issue, or a pull Request if you find any bug or if you have some idea for improvement.

### License

This project is licensed under the terms of the [MIT license](https://opensource.org/licenses/MIT).