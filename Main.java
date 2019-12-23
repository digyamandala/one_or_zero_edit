import java.util.HashMap;

class Main {
  public static void main(String[] args) {
    String a = "pale";
    String b = "palle";
    HashMap<Character, Integer> c = new HashMap<>();
    HashMap<Character, Integer> d = new HashMap<>();
    HashMap<Character, Boolean> visited = new HashMap<>();
    boolean res = true;
    int changes = 0;

    if (a.length() == b.length()) {
      for (int i = 0; i < a.length(); i++) {
        char x = a.charAt(i);

        if (c.getOrDefault(x, -1) != -1) {
          c.replace(x, c.get(x) + 1);
        } else {
          c.put(x, 1);
        }
      }

      for (int i = 0; i < b.length(); i++) {
        char x = b.charAt(i);
        if (c.getOrDefault(x, -1) == -1) {
          ++changes;
        }

        if (changes > 1) {
          res = false;
          break;
        }
      }
    } else {
      if (Math.abs(a.length() - b.length()) > 1) {
        res = false;
      } else {
        String maxString = max(a, b);
        String minString = min(a, b);
        for (int i = 0; i < maxString.length(); i++) {
          char x = maxString.charAt(i);
          if (c.getOrDefault(x, -1) == -1) {
            c.put(x, 1);
          } else {
            c.replace(x, c.get(x) + 1);
          }
        }

        for (int i = 0; i < minString.length(); i++) {
          char x = minString.charAt(i);
          if (d.getOrDefault(x, -1) == -1) {
            d.put(x, 1);
          } else {
            d.replace(x, d.get(x) + 1);
          }
        }

        for (int i = 0; i < maxString.length(); i++) {
          char x = maxString.charAt(i);
          if (d.getOrDefault(x, -1) == -1) {
            ++changes;
          } else {
            if (d.get(x) != c.get(x)) {
              if (!visited.getOrDefault(x, false)) {
                changes = changes + Math.abs(d.get(x) - c.get(x));
                visited.put(x, true);
              }
            }
          }

          if (changes > 1) {
            res = false;
            break;
          }
        }
      }
    }

    System.out.println(a + " to " + b + " " + res + " with " + changes + " edit(s)");

  }

  private static String max(String a, String b) {
    if (a.length() > b.length())
      return a;
    return b;
  }

  private static String min(String a, String b) {
    if (a.length() <= b.length())
      return a;
    return b;
  }
}