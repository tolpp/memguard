# memguard [![Build Status](https://travis-ci.org/tolpp/memguard.svg?branch=master)](https://travis-ci.org/tolpp/memguard)
Memguard is a lightweight and extensible memory security and protection library for Java and Android applications.
Memguard protects your sensitive data in memory against memory editing software (Like trainers, GameGuardian in Android etc..). 

### Installation
##### For Gradle
Add dependencies to your projects build.gradle file
```groovy
dependencies {
    compile 'com.tolpp.memguard:memguard:0.0.1'
    // compile 'com.tolpp.memguard:memguard-serializer-fst:0.0.1' // uncomment if you will use fst serializer
}
```

##### For Maven
Add dependencies to your projects pom.xml file.
```xml
<dependency>
    <groupId>com.tolpp.memguard</groupId>
    <artifactId>memguard</artifactId>
    <version>0.0.1</version>
</dependency>
``` 

### Simple Usage
```java
Guard<Integer> score = new Guard.Builder<Integer>().value(10).build();
score.set(20);
Integer unboxedInt = score.get();
```

### Customizing Serializer
New serializer can be given during creating builder.
```java
new Guard.Builder<String>()
    .serializer(FstSerializer.create())
    .build()
```

### Customizing Encoder
Encoders can be given via encoder factories to the builder. If you give more than one encoder, 
Guard encodes serialized variable as same order as the your adding order of the encoder factories.

```java
new Guard.Builder<String>()
    .addEncoderFactory(RandomXorEncoderFactory.create())
    .addEncoderFactory(YourEncoderFactory.create())
    .build()
```

By default, memguard uses Java's default serializer as serializer and RandomXorEncoder as variable encoder.

### TODO's
- [x] Continuous integration
- [ ] Wiki page
- [x] Add project to maven central
- [ ] Add RSA Encryption encoder

### License
Apache 2.0
 

