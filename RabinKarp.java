import java.util.Locale;

public class RabinKarp {

    public void StringMatching(String pattern, String text)
    {
      // d is the number of characters in the input alphabet
      int d = 256 ;
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
        patternHash = ((d * patternHash) + pattern.charAt(i)) % q;
        textHash = ((d * textHash) + text.charAt(i)) % q;
      }
      
      // Moving the pattern over text one by one
      for (int i = 0; i <= textLength - patternLength; i++)
      {
        //Comparing the hash value pf pattern with that of text
        if ( patternHash == textHash ) {
          // If the both hashes are equal , the algorithm proceeds for a character by character
          // comparison
          int j;
          for (j = 0; j < patternLength; j++) {
            if (text.charAt(i + j) != pattern.charAt(j))
              // Check for spurious hits
              break;
          }

          // if patternHash == textHash and pattern[0...M-1] = text[i, i+1, ...i+M-1]
          if (j == patternLength) {
            System.out.println("Pattern found at index " + i);
          }
        }

        // Calculate hash value for next window of text by removing
        // hash value of leading character  and adding hash value of trailing character
        if ( i < textLength-patternLength )
        {
          textHash = (d*(textHash - text.charAt(i)*hash) + text.charAt(i+patternLength))%q;
          // In case of negative hash , we will convert it to positive value
          if (textHash < 0) {
            textHash = (textHash + q);
          }
        }
      }
    }


    public static void main(String[] args)
    {
      RabinKarp rbk = new RabinKarp();
      String text = "ADOBOBIPARTISAN";
      String pattern = "RABIN";
      rbk.StringMatching(pattern.toUpperCase(Locale.ROOT), text.toUpperCase());
    }
  }


