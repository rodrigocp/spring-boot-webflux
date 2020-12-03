### Build the project
echo "Build Projects..."
gradle clean build -x test

### Set minikube docker environment
echo "Setting docker environment..."
eval "$(minikube docker-env)"

### Build docker images
echo "Building docker images..."
docker build -t account-service ./account
docker build -t account-service ./gateway

### Recreate secret and mongo pods
echo "Deleting pods..."
kubectl delete -f kubernetes/mongo/deployment.yaml
kubectl delete -f kubernetes/mongo/service.yaml
kubectl delete -f kubernetes/mongo/volume.yaml

kubectl delete -f kubernetes/postgres/deployment.yaml
kubectl delete -f kubernetes/postgres/service.yaml
kubectl delete -f kubernetes/postgres/volume.yaml

kubectl delete -f account/kubernetes/deployment.yaml
kubectl delete -f account/kubernetes/service.yaml

kubectl delete -f gateway/kubernetes/deployment.yaml
kubectl delete -f gateway/kubernetes/service.yaml

###
echo "Creating pods..."
kubectl create -f kubernetes/mongo/volume.yaml
kubectl create -f kubernetes/mongo/service.yaml
kubectl create -f kubernetes/mongo/deployment.yaml

kubectl create -f kubernetes/postgres/volume.yaml
kubectl create -f kubernetes/postgres/service.yaml
kubectl create -f kubernetes/postgres/deployment.yaml

kubectl create -f account/kubernetes/service.yaml
kubectl create -f account/kubernetes/deployment.yaml

kubectl create -f gateway/kubernetes/service.yaml
kubectl create -f gateway/kubernetes/deployment.yaml