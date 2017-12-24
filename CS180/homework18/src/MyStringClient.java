public class MyStringClient {
    public static void main(String[] args) throws IllegalIndexException{
        MyString m = new MyString();
        char[] test = {'a','b','c'};
        char[] test2 = {'d','e','f'};
        m.append(test);
        m.prepend(test2);
        m.removeChars(0,4);
        System.out.println(m.getData());
    }
}
