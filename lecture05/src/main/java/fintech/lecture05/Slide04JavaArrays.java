package fintech.lecture05;

public class Slide04JavaArrays {

  public static final void assignment() {
    String[] strings = {"str"};
    Object[] objects = strings;
    objects[0] = new Integer(1);
    String s = strings[0];
  }

  public final static void main(String[] args) {
    assignment();
  }

  public void sort(Object[] xs, java.util.Comparator cmp) {
    // sorting...
  }
}
