git clone https://${USERNAME}:${ACCESS_TOKEN}@github.com/cse364-unist/projects-group2.git

cd /app/projects-group2

git checkout milestone2

chmod -R 755 .

cd /app/projects-group2/milestone2

mvn clean test jacoco:report

# mvn clean package

mvn spring-boot:run

cd /app/projects-group2/milestone2/frontend
serve -s build -l 3000