gcloud auth login

docker tag com.sra/inventory.service:$1 asia-southeast2-docker.pkg.dev/com-sra/com-sra-repository/inventory.service:$1
#docker tag com.sra/order.service:$1 asia-southeast2-docker.pkg.dev/com-sra/com-sra-repository/order.service:$1

docker push asia-southeast2-docker.pkg.dev/com-sra/com-sra-repository/inventory.service:$1
#docker push asia-southeast2-docker.pkg.dev/com-sra/com-sra-repository/order.service:$1

