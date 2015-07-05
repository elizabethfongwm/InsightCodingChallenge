/**
 * Analyzes each word in each tweet in a stream of tweets. Also provides the 
 * running median of the number of unique words in each tweet.
 * 
 * @author Elizabeth Fong
 * @version 6th July 2015
 */
public class TwitterAnalysis
{
    // constants - input
    private static final String INPUT_FILE = "tweet_input/tweets.txt" ;
    private static final String CHARSET_NAME = "US-ASCII" ;
    
    // constants - output
    private static final String WORD_COUNT_FILE = "tweet_output/ft1.txt" ;
    private static final String MEDIAN_FILE = "tweet_output/ft2.txt" ;
    
    // reads the input
    private StreamReader _input ;
    
    // Unique words and # occurrences - all tweets
    private WordsAnalysis _words ;
    
    // Running median
    private RunningMedian _median ;
    
    
    /* --- METHODS ---------------------------------------------------------- */
    
    /**
     * Constructor. Runs the program. Analyzes each word in each tweet in a
     * stream of tweets. Also provides the running median of the number of 
     * unique words in each tweet.
     */
    public TwitterAnalysis()
    {
        
        _input = new StreamReader( INPUT_FILE , CHARSET_NAME ) ;
        _words = new WordsAnalysis( WORD_COUNT_FILE ) ;
        _median = new RunningMedian( MEDIAN_FILE ) ;
        
        // analyse each tweet - print running median
        while( _input.hasNextTweet() )
        {
            _input.nextTweet() ;
            analyseTweet() ;
            
            _median.printRunningMedian() ;
        }
        
        _median.closeWriter() ;
        
        // complete analysing all tweets - print word counts
        _words.printWordCounts() ;
    }
    
    /**
     * Analyzes each word in a tweet.
     */
    private void analyseTweet()
    {
        WordsAnalysis tweet = new WordsAnalysis() ;
        
        while( _input.tweetHasNextWord() )
        {
            tweet.addWord( _input.tweetNextWord() ) ;
        }
        
        _median.add( tweet.numUniqueWords() ) ;
        _words.addWords( tweet ) ;
    }
    
    
    /* --- MAIN ------------------------------------------------------------- */
    
    /**
     * Main method. Runs the program.
     * 
     * @param args None expected.
     */
    public static void main( String[] args )
    {
        new TwitterAnalysis() ;
    }
}