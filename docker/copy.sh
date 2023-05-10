#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20230223.sql ./mysql/db
cp ../sql/ry_config_20220929.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../weile-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy weile-gateway "
cp ../weile-gateway/target/weile-gateway.jar ./ruoyi/gateway/jar

echo "begin copy weile-auth "
cp ../weile-auth/target/weile-auth.jar ./ruoyi/auth/jar

echo "begin copy weile-visual "
cp ../weile-visual/weile-monitor/target/weile-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy weile-modules-system "
cp ../weile-modules/weile-system/target/weile-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy weile-modules-file "
cp ../weile-modules/weile-file/target/weile-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy weile-modules-job "
cp ../weile-modules/weile-job/target/weile-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy weile-modules-gen "
cp ../weile-modules/weile-gen/target/weile-modules-gen.jar ./ruoyi/modules/gen/jar

