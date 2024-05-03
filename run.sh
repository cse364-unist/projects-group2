git clone https://github.com/cse364-unist/projects-group2.git

cd projects-group2

git checkout milestone2

chmod -R 755 .

cd milestone2

mvn clean test jacoco:report

# mvn clean package

mvn spring-boot:run