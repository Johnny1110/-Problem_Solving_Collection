# protobuf 的 maven 配置

<br>

---

<br>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.frizo.lib</groupId>
    <artifactId>grpc_node_1</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>grpc node 1</name>

    <properties>
        <spring.boot.version>2.2.1.RELEASE</spring.boot.version>
        <maven.compiler.version>3.8.1</maven.compiler.version>
        <maven.resource.version>3.1.0</maven.resource.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <version>${spring.boot.version}</version>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java -->
        <dependency>
            <groupId>com.google.protobuf</groupId>
            <artifactId>protobuf-java</artifactId>
            <version>3.23.1</version>
        </dependency>



    </dependencies>



    <build>

        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.6.2</version>
            </extension>
        </extensions>

        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven.compiler.version}</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>${maven.resource.version}</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring.boot.version}</version>
                <configuration>
                    <mainClass>#</mainClass>
                    <layout>ZIP</layout>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.6.1</version>
                <extensions>true</extensions>
                <configuration>
                    <!-- 工具版本 -->
                    <protocArtifact>com.google.protobuf:protoc:3.23.1:exe:${os.detected.classifier}</protocArtifact>
                    <!--默认值，proto源文件路径-->
                    <protoSourceRoot>${project.basedir}/src/main/proto</protoSourceRoot>
                    <!--默认值，proto目标java文件路径-->
                    <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
                    <!--设置是否在生成java文件之前清空outputDirectory的文件，默认值为true，设置为false时也会覆盖同名文件-->
                    <clearOutputDirectory>false</clearOutputDirectory>

                </configuration>
                <executions>
                    <execution>
                        <!--在执行mvn compile的时候会执行以下操作-->
                        <phase>compile</phase>
                        <goals>
                            <!--生成OuterClass类-->
                            <goal>compile</goal>
                            <!--生成Grpc类-->
                            <!--goal>compile-custom</goal-->
                        </goals>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>

</project>
```

<br>
<br>

proto 文件定義在 src/main/proto 裡就可以了

<br>

範例文件 OrderStatus.proto:

```
syntax = "proto3";

package com.example.order;

message OrderStatus {
  string orderId = 1;
  string status = 2;
}

service OrderStatusService {
  rpc UpdateOrderStatus(OrderStatus) returns (OrderStatus) {}
}
```

<br>
<br>

執行 plugin 的 compile 就可以得到編譯過後的文件

```
mvn protobuf:compile
```

