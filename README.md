# memguard [![Build Status](https://travis-ci.org/tolpp/memguard.svg?branch=master)](https://travis-ci.org/tolpp/memguard)
Memguard is a lightweight and an extensible memory security and protection framework for Java and Android applications.
Memguard protects your sensitive values in memory against memory editing softwares. 

### Installation
Memguard is not in any maven repository currently. Clone repository to your locale and then:

Include project in settings.gradle file
```groovy
include 'memguard'
// include 'memguard-serializer-fst' // uncomment if you will use fst serializer

project(':memguard').projectDir = new File('../memguard/memguard')
// project(':memguard-serializer-fst').projectDir = new File('../memguard/serializer/fst')
``` 

Add dependency using build.gradle file
```groovy
dependencies {
  compile project(':memguard')
  // compile project(':memguard-serializer-fst') // uncomment if you will use fst serializer
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
- [x] Continuous integration
- [ ] Wiki page
- [ ] Add project to maven central.
- [ ] Add RSA Encryption encoder

### Licanse
Apache 2.0
 

