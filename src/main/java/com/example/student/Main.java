package com.example.student;

import com.example.springdemo.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /*StudentDao dao = new StudentDao();

        try(Scanner sc = new Scanner(System.in)){
            while(true){

                System.out.println("\n====学生管理系统=====");
                System.out.println("1.查询所有学生");
                System.out.println("2.根据ID查询学生");
                System.out.println("3.添加学生");
                System.out.println("4.修改学生成绩");
                System.out.println("5.删除学生");
                System.out.println("0.退出系统");
                System.out.println("请输入你的选择：");

                String choice = sc.nextLine();

                try{
                    switch (choice){
                        case "1":{
                            for(Student s:dao.findall()){
                                System.out.println(s);
                            }
                            break;
                        }
                        case "2":{
                            System.out.println("请输入学生ID：");

                            int id =Integer.parseInt(sc.nextLine());

                            Student student = dao.findById(id);
                            //String input = sc.nextLine();第一步：读取用户输入,int id = Integer.parseInt(input);第二步：转换成整数
                            if(student!=null){
                                System.out.println(student);
                            }else{
                                System.out.println("学生不存在！");
                            }
                            break;
                        }
                        case "3":{
                            System.out.println("请输入姓名、年龄、成绩，用空格分隔：");

                            String input = sc.nextLine();

                            String[] parts = input.trim().split("\\s+");
                            //这行代码做了两件事：
                            //trim()：删除字符串开头和结尾的空白。
                            //split("\\s+")：按照一个或多个空白字符拆分字符串。
                            //注意，三个元素仍然都是字符串，所以后两个需要转换为数字。这个简单版本暂时要求姓名中不含空格。
                            if (parts.length != 3) {
                                System.out.println("输入格式错误！例如：Alice 20 95.5");
                                break;
                            }

                            String name = parts[0];
                            int age = Integer.parseInt(parts[1]);
                            double score = Double.parseDouble(parts[2]);

                            if (age <= 0 || !Double.isFinite(score)
                                    || score < 0 || score > 100) {
                                System.out.println("年龄或成绩不合法！");
                                break;
                            }

                            int row = dao.addStudent(name,age,score);

                            if(row>0){
                                System.out.println("添加学生成功！");
                            }else{
                                System.out.println("添加学生失败！");
                            }
                            break;
                        }
                        case "4":{
                            System.out.println("请输入学生 ID 和新成绩，用空格分隔：");

                            String[] parts = sc.nextLine().trim().split("\\s+");

                            if (parts.length != 2) {
                                System.out.println("输入格式错误！例如：3 95.5");
                                break;
                            }

                            int id = Integer.parseInt(parts[0]);
                            double score = Double.parseDouble(parts[1]);

                            if (!Double.isFinite(score) || score < 0 || score > 100) {
                                System.out.println("成绩不合法！");
                                break;
                            }

                            int row = dao.updateSCore(id,score);

                            if(row>0){
                                System.out.println("修改成绩成功！");
                            }else{
                                System.out.println("修改失败：学生不存在!");
                            }
                            break;
                        }
                        case "5":{
                            System.out.println("请输入要删除的学生ID:");

                            int id = Integer.parseInt(sc.nextLine());

                            int row = dao.deleteById(id);

                            if(row>0){
                                System.out.println("添加学生成功！");
                            }else{
                                System.out.println("添加学生失败！");
                            }
                            break;
                        }
                        case "0":{
                            System.out.println("已退出系统");
                            return;//return：结束整个 main 方法
                        }
                        default:{
                            System.out.println("输入格式错误，请输入数字");
                        }
                    }
                }catch(SQLException e){
                    System.out.println("数据库操作失败：" + e.getMessage());
                }catch(NumberFormatException e){//NumberFormatException 的意思是：数字格式异常。它主要发生在程序尝试把一个不符合格式的字符串转换为数字时。
                    //例如：int id = Integer.parseInt("abc");这里 "abc" 并不是合法的整数，所以转换失败，Java 就会抛出 NumberFormatException。
                    System.out.println("数字格式错误，请重新输入！");
                }
            }
        }*/
        try(AnnotationConfigApplicationContext context=
                    new AnnotationConfigApplicationContext(com.example.student.AppConfig.class);
        ){
            System.out.println(
                    "正在使用的配置类：" + AppConfig.class.getName()
            );

            System.out.println(
                    "StudentService 是否存在：" +
                            context.containsBean("studentService")
            );
            StudentService service = context.getBean(StudentService.class);

            try{
                service.showAllStudents();
            }catch(SQLException e){
                System.out.println(
                        "数据库查询失败:"+e.getMessage()
                );
            }
        }



    }
}
//什么是 Maven？
//Maven 是 Java 生态常用的项目管理和构建工具，主要负责：
//依赖管理： 下载并管理项目需要的第三方库。
//统一项目结构： 按照约定的位置组织源代码和资源文件。
//项目构建： 编译、测试和打包项目。
//例如，当你在配置文件中声明需要 MySQL 驱动后，Maven 就能够从配置的仓库获取该驱动及其必要的传递依赖。
//Java 负责实现程序功能，Maven 负责管理依赖和构建项目，IDEA 则是你编写、调试和运行代码的开发工具。
//它们不是互相替代的关系，而是一起配合工作。
//认识 Maven 项目的标准目录
//其中最重要的四个位置：
//位置                                   作用
//pom.xml                               声明依赖、版本以及构建配置
//src/main/java                         放正式的 Java 源代码
//src/main/resources                    放项目资源及适当的配置文件
//src/test/java                         放测试代码
//现在先记住：以后编写 Spring Boot 项目时，主要 Java 代码就放在 src/main/java 下。
//------------------------------
//在 IntelliJ IDEA 中创建 Maven 项目
//今天建议新建独立项目，不直接改动 Day 35 的旧工程。这样旧代码可以作为备份，也方便你对比 Maven 改造前后的区别。
//第一步：新建项目
//打开你使用的中文版 IntelliJ IDEA：
//1.选择「文件 → 新建 → 项目」。
//2.项目名称填写 student-management。
//3.选择 Maven 构建系统；不同 IDEA 版本中，可能直接选择 Maven 项目，也可能需要先选择 Java，再选择 Maven。
//4.JDK 选择你已经安装的 JDK 25。
//5.选择项目保存位置，然后创建项目。
//-------------------------------
//认识 pom.xml pom.xml 是 Maven 的核心配置文件。
//先看一个简单版本：
//<project xmlns="http://maven.apache.org/POM/4.0.0"
//         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
//         https://maven.apache.org/xsd/maven-4.0.0.xsd">
//
//    <modelVersion>4.0.0</modelVersion>
//
//    <groupId>com.example</groupId>
//    <artifactId>student-management</artifactId>
//    <version>1.0-SNAPSHOT</version>
//
//</project>
//今天只需要理解三个概念：
//配置项                含义                       当前例子
//groupId              项目所属组织或命名空间         com.example
//artifactId           项目的名称或标识              student-management
//version              当前项目版本                  1.0-SNAPSHOT
//这三个信息组合起来，可以用来标识一个 Maven 构件。SNAPSHOT 一般表示开发中的版本。
//--------------------------------
//通过 Maven 自动引入 MySQL 驱动
//以前你需要将 mysql-connector-j-26.7.0.jar 手动添加到项目中。
//现在，只需要在 pom.xml 中声明这个依赖：
//<dependencies>
//    <dependency>
//        <groupId>com.mysql</groupId>
//        <artifactId>mysql-connector-j</artifactId>
//        <version>26.7.0</version>
//    </dependency>
//</dependencies>
//1.dependency 是什么意思？
//相当于告诉 Maven：我的 Java 项目需要这个外部库，请帮我准备好它。
//Maven 会根据你提供的 groupId、artifactId、version 确定需要哪个依赖。
//2.完整的 pom.xml
//为了便于你学习和复制，我们今天使用下面这份简单配置：
//<project xmlns="http://maven.apache.org/POM/4.0.0"
//         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
//         https://maven.apache.org/xsd/maven-4.0.0.xsd">
//
//    <modelVersion>4.0.0</modelVersion>
//
//    <groupId>com.example</groupId>
//    <artifactId>student-management</artifactId>
//    <version>1.0-SNAPSHOT</version>
//
//    <properties>
//        <maven.compiler.release>21</maven.compiler.release>
//        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
//    </properties>
//
//    <dependencies>
//        <dependency>
//            <groupId>com.mysql</groupId>
//            <artifactId>mysql-connector-j</artifactId>
//            <version>26.7.0</version>
//        </dependency>
//    </dependencies>
//
//</project>
//这里有个值得注意的新配置：<maven.compiler.release>21</maven.compiler.release>
//它表示我们目前按 Java 21 的语言和 API 级别编译项目。即使你使用 JDK 25，也可以先按 Java 21 编译，便于后续使用长期支持版本的 Java 环境。
//注意，这不会安装 JDK 21，也不会更改你电脑的 JDK 25。你需要把 main 写为 public static void main(String[] args)，避免依赖较新版本的简化入口写法。
//3.修改完 pom.xml 后怎么办？
//在 IDEA 中，一般会出现加载或同步 Maven 项目的提示。点击相应按钮。
//如果没有提示，可以打开右侧 Maven 工具窗口，点击「重新加载所有 Maven 项目」（通常是刷新图标）。
//首次下载依赖需要网络连接。成功后，可以在 IDEA 的外部库中查看 MySQL 驱动；本地 Maven 仓库通常位于
//C:\Users\你的Windows用户名\.m2\repository
//----------------------------------
//认识 Maven 常用命令
//Maven 有一套构建生命周期，常用阶段如下：
//1.validate 检查项目是否具备正确构建所需的基本信息。
//2.compile 编译 Java 源代码。
//3.test 编译并执行配置好的测试。
//4.package 将项目打包成 JAR 等文件。
//5.install 将构建出来的构件安装到本地 Maven 仓库。
//Maven 执行某个阶段时，会先执行它之前的相关生命周期阶段，例如执行 package 时会包含编译和测试阶段
//今天先掌握下面三个命令：
//命令                              功能
//mvn compile                      编译项目
//mvn test                         执行测试阶段
//mvn package                      打包项目
