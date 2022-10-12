import java.util.Random;
public class Students {
    public static void main(String[] args) {
        int sum = 0;
        for (int k = 0; k < 5; k++) {
            int[] num = new int[21];
//  初始化班级成员编号以及迟到次数
            Student[] students = new Student[100];
            Init(students);
//  第一次点名随机生成，并且全点
            First(students);
//  剩下19次点名
            notFirst(students, num);
//  输出从2到19次点名的有效次数
            for (int i = 2; i <= 20; i++) {
                sum+= num[i];
            }
        }
        System.out.println(sum/(450.00+190*5));
    }
//  定义学生类，id是唯一标识，absent是缺席次数，tag标记为经常迟到的5~8人
    static class Student {
        int id;
        int absent;
        int tag;
    }
//  初始化函数，缺席次数与标记都为0
    static void Init(Student[] students)
    {
        for (int i = 0; i <=99; i++) {
            students[i] = new Student();
            students[i].id = i;
            students[i].absent = 0;
            students[i].tag = 0;
        }
    }
//  第一次点名完全随机生成并标记x个经常迟到的学生
    static  void First(Student[] students){
        Random random = new Random();
        int x =random.nextInt(10);
        while(x>8||x<5) x= random.nextInt(10);
        int count = 0;
        for (int i = 1; i <= 90; i++) {
//            第一次生成完全随机，控制缺课人数在八左右
            if (random.nextInt(100) < 9)
            {
                students[i].absent++;
                if(count<x)
                {
                    students[i].tag=1;
                    count++;
                }
            }
        }
//  根据迟到次数排序，降序排列
        ObjSort(students);
    }
    static  void notFirst(Student[] students,int[] number){
        Random random =new Random();
//        2~19次的点名
        for(int count=2;count<=20;count++) {
            int valid=0;
            for (int i = 1; i <= 90; i++)
            {
//                如果是被标记了的，会有一个大概率缺课
                if(students[i].tag==1)
                {
                    if(random.nextInt(1000)<800)
                    {
                        students[i].absent++;
//  每次点名都会排序，最经常迟到的会排在最前面，为了使E最大只点名最前面的十个人，点到没来次数加一
                        if(i<10)  valid++;
                    }
                }
//                否则只会有个小概率缺课（反正都会漏点，为了让E更大漏点无所谓
//                （就像偶尔生病请假等小概率没必要浪费算法规模））
               else {
                   int a= random.nextInt(1000);
                   while (a>((3.000/82)*1000))
                   {
                       a=random.nextInt(1000);
                   }
                if(random.nextInt(1000)<a) students[i].absent++;
                if(i<=10)
                    {
                        students[i].absent++;
                        valid++;
                    }
                }
            }
            number[count]=valid;
            ObjSort(students);
        }
    }
//    排序算法，缺课次数多的排前面
    static  void  ObjSort(Student[] students){
        for(int i =0;i<=90;i++){
            for(int j=0;j<=90-i;j++){
                if(students[j].absent<students[j+1].absent)
                {
                    Student t = students[j];
                    students[j]=students[j+1];
                    students[j+1]=t;
                }
            }
        }
    }
}



