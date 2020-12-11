### Build the project
echo "Build Projects..."
gradle clean build -x test

### Set minikube docker environment
echo "Setting docker environment..."
eval "$(minikube docker-env)"

### Build docker images
echo "Building docker images..."
docker build -t account-service ./account
docker build -t gateway-service ./gateway

###
echo "Creating pods..."
kubectl create -f kubernetes/persistent-volume.yaml
kubectl create -f kubernetes/persistent-claim.yaml

kubectl create -f kubernetes/postgres-service.yaml
kubectl create -f kubernetes/mongo-service.yaml
kubectl create -f kubernetes/elasticsearch-service.yaml
kubectl create -f kubernetes/kibana-service.yaml
kubectl create -f kubernetes/logstash-service.yaml

kubectl create -f kubernetes/logstash-configmap.yaml

kubectl create -f kubernetes/elasticsearch-deployment.yaml
kubectl create -f kubernetes/kibana-deployment.yaml
kubectl create -f kubernetes/logstash-deployment.yaml
kubectl create -f kubernetes/mongo-deployment.yaml
kubectl create -f kubernetes/postgres-deployment.yaml

kubectl create -f account/kubernetes/service.yaml
kubectl create -f account/kubernetes/deployment.yaml

kubectl create -f gateway/kubernetes/service.yaml
kubectl create -f gateway/kubernetes/deployment.yaml

### Unset minikube docker environment
echo "Unsetting docker environment..."
eval "$(minikube docker-env -u)"