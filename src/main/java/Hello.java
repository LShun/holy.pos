public class Hello {
    private int hello1;
    private int hello2;
    private String hello3;

    public Hello(int hello1, int hello2, String hello3) {
        this.hello1 = hello1;
        this.hello2 = hello2;
        this.hello3 = hello3;
    }

    public Hello() {
    }

    public int getHello1() {
        return hello1;
    }

    public void setHello1(int hello1) {
        this.hello1 = hello1;
    }

    public int getHello2() {
        return hello2;
    }

    public void setHello2(int hello2) {
        this.hello2 = hello2;
    }

    public String getHello3() {
        return hello3;
    }

    public void setHello3(String hello3) {
        this.hello3 = hello3;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "hello1=" + hello1 +
                ", hello2=" + hello2 +
                ", hello3='" + hello3 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hello hello = (Hello) o;

        if (getHello1() != hello.getHello1()) return false;
        if (getHello2() != hello.getHello2()) return false;
        return getHello3() != null ? getHello3().equals(hello.getHello3()) : hello.getHello3() == null;
    }

    @Override
    public int hashCode() {
        int result = getHello1();
        result = 31 * result + getHello2();
        result = 31 * result + (getHello3() != null ? getHello3().hashCode() : 0);
        return result;
    }
}
