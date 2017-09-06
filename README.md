# memguard
Memguard is a lightweight and an extensible memory security and protection framework for Java and Android applications.
Memguard protect your sensitive values in memory against memory editing softwares. 

### Installation
Memguard is not in any maven repository currently. Clone repository to your locale and then:

Include project settings.gradle
```groovy
include 'memguard-root'
project(':memguard-root').projectDir = new File('../../memguard')
``` 

Add dependency via build.gradle
```groovy
dependencies {
  compile project(':memguard')
}

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
- [] Continuous integration
- [] Wiki page
- [] Add project to maven central.
- [] Add RSA Encryption encoder

### Licanse
Apache 2.0
 

