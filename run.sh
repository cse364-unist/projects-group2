git clone https://${USERNAME}:${ACCESS_TOKEN}@github.com/cse364-unist/projects-group2.git

cd /app/projects-group2

git checkout milestone3

chmod -R 755 .


cd /app/projects-group2/milestone2/frontend
npm install
npm run build
npm install -g serve
serve -s build -l 3000 > /dev/null 2>&1 &

cd /app/projects-group2/milestone2

mvn clean test jacoco:report

# mvn clean package

mvn spring-boot:run
