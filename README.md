全程完美跑通 遇到的所有bug均已解决

# 运行代码前请阅读以下信息
运行前请确保:
1.数据库:自己数据库中有tlias架构并且已经按照黑马给的sql语句建好表了
2.云服务:确保阿里云OSS已经按黑马给的教程配好了

运行步骤:
1.下载后解压,将nginx-1.22.0-tlias和tlias-project两个文件夹转移到无中文的路径
2.用IDEA中打开tlias-project文件夹
3.打开在其resources目录下application.yml文件,配置数据库与阿里云OSS的相关信息
4.刷新Maven
5.运行启动类TliasProjectApplication.java
6.打开第1步中转移的nginx-1.22.0-tlias文件夹,双击运行里面的nginx.exe
7.打开浏览器,访问网址http://localhost:90/

其余代码:
1.Day14的视频SpringBoot原理,阿里云OSS自动配置的代码
2.Day15的视频Maven高级,对项目进行分模块开发与完成继承与聚合
以上代码均已跑通,打成压缩包放在了另一个分支(左上角main分支下还有另一个code分支)
