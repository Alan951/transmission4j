mvn clean package
mvn install:install-file -Dfile=./target/transmission4j.jar -DgroupId=com.jalan -DartifactId=transmission4j -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true