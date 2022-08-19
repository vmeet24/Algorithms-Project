import java.util.Locale;

public class RabinKarpAdvanced {

  public void StringMatching(String pattern, String text)
  {
    // d is the number of characters in the input alphabet
    int d = 256;
    // Length of pattern
    int patternLength = pattern.length();
    // Length of text
    int textLength = text.length();
    int hash = 1;
    // q represents prime number to be used for modulo in hash function
    int q = 13;

    // Generating the hash
    for (int i = 0; i < patternLength-1; i++) {
      hash = (hash*d)%q;
    }

    // hash value for pattern
    int patternHash = 0;
    // hash value for text
    int textHash = 0;
    // Calculating the hash value of pattern and window of first text
    for (int i = 0; i < patternLength; i++)
    {
      patternHash = (d*patternHash + pattern.charAt(i))%q;
      textHash = (d*textHash + text.charAt(i))%q;
    }

    // quotient for pattern
    int patternQuotient = 0;
    // quotient for pattern
    int textQuotient = 0;
    // Calculating the quotient of pattern and text
    for (int i = 0; i < patternLength; i++)
    {
      patternQuotient = (d*patternHash + pattern.charAt(i))/q;
      textQuotient = (d*textHash + text.charAt(i))/q;
    }

    // Moving the pattern over text one by one
    for ( int i = 0; i <= textLength - patternLength; i++)
    {

      //Comparing the hash value of pattern with that of text and comparing quotient of pattern with text
      // This step would never generate spurious hits , thereby reducing the need for character-by-character comparison
      if (( patternHash == textHash ) && (patternQuotient == textQuotient))
      {
        System.out.println("Pattern found at index " + i);
      }

      // Calculate hash value for next window of text by removing
      // hash value of leading character  and adding hash value of trailing character
      if ( i < textLength-patternLength )
      {
        textHash = (d*(textHash - text.charAt(i)*hash) + text.charAt(i+patternLength))%q;
        textQuotient = (d*(textHash - text.charAt(i)*hash) + text.charAt(i+patternLength))/q;
        // In case of negative hash , we will convert it to positive value
        if (textHash < 0) {
          textHash = (textHash + q);
        }
      }
    }
  }


  public static void main(String[] args)
  {
    RabinKarpAdvanced rbka = new RabinKarpAdvanced();
    String text = "ADOBOBIPARTISAN";
    String pattern = "RABIN";
    rbka.StringMatching(pattern.toUpperCase(Locale.ROOT), text.toUpperCase(Locale.ROOT));
  }

}
