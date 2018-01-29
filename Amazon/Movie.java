/*
 * 假设有个Movie类，
 * public class Movie
 * {
 *    int movieId;.
 *    float rating;
 *    List<Movie> similarMovies
 * 还有其他的getters
 * }
 * 现在要求找到 k个和movie最相似 的movies。
 * 函数的signature大概是这样的：
 * public static List<Movie> getNearest(Movie movie, int numSimilar)。
 *
 * 举个栗子：
 * m0 <-->m1, similarity 2
 * mo <--> m2, similarity 3
 * m1 <--> m3, similarity 4
 * m2 <--> m5, similaity 5
 *
 * 如果要返回和mo最相似的movie, 那么应该返回 m5 (只有有一条路径从 m1到 m5, 并且 5是最大的）；
 * 如果返回3个最相似的就返回 m2, m3， m5（顺序不重要）； 如果需要返回10个，但是相似的只有9个，那么就返回9个。
 * source movie本身不能在返回结果里面。
 *
 * 可以的一个做法是 dfs + min-Heap(PriorityQueue)， 我们一直做dfs， 每次碰到一个新的movie，如果现在queue的size比
 * k小的话，直接加到queue里面；  *  * 如果新movie的rating比queue top movie的rating高的话， 把顶部movie
 * 踢出队列，加上这个新的。
 *
 * update: 应该返回 m5 (只有有一条路径从 m1到 m5, 并且 5是最大的） --> 应该返回 m5 (只要有一条路径从 m1到 m5, 并且 5是最大的）
 *
 *
 * Sample code from: https://github.com/gszeliga/algorithms-and-more/blob/master/src/main/java/com/gzeliga/playground/algorithms/amazon/Movie.java
 *
 *
 */
import java.util.*;
public class Movie {

	private final int movieId;
	private final float rating;
	private List<Movie> similarMovies; // Similarity is bidirectional

	public Movie(int movieId, float rating) {
		this.movieId = movieId;
		this.rating = rating;
		similarMovies = new ArrayList<Movie>();
	}

	public int getId() {
		return movieId;
	}

	public float getRating() {
		return rating;
	}

	public void addSimilarMovie(Movie movie) {
		similarMovies.add(movie);
		movie.similarMovies.add(this);
	}

	public List<Movie> getSimilarMovies() {
		return similarMovies;
	}

    /*
	 * Implement a function to return top rated movies in the network of movies
	 * reachable from the current movie eg: A(Rating 1.2) / \ B(2.4) C(3.6) \ /
	 * D(4.8) In the above example edges represent similarity and the number is
	 * rating. getMovieRecommendations(A,2) should return C and D (sorting order
	 * doesn't matter so it can also return D and C)
	 * getMovieRecommendations(A,4) should return A, B, C, D (it can also return
	 * these in any order eg: B,C,D,A) getMovieRecommendations(A,1) should
	 * return D. Note distance from A to D doesn't matter, return the highest
	 * rated.
	 *
	 * @param movie
	 *
	 * @param numTopRatedSimilarMovies number of movies we want to return
	 *
	 * @return List of top rated similar movies
	 */

	public static List<Movie> getMovieRecommendations(Movie movie,
			int numTopRatedSimilarMovies) {
		// BFS and Priority Queue
        List<Movie> recoms = new ArrayList<Movie>();
        PriorityQueue<Movie> recomQueue = new PriorityQueue<Movie>(numTopRatedSimilarMovies,
            (Movie a, Movie b) -> Float.compare(a.getRating(), b.getRating())
            );
        Queue<Movie> queue = new LinkedList<Movie>();
        BitSet visited = new BitSet();
        queue.offer(movie);
        visited.set(movie.getId());
        while(!queue.isEmpty()) {
            Movie curr = queue.poll();
            if (!visited.get(curr.getId())) {
                visited.set(curr.getId());
                if (recomQueue.size() < numTopRatedSimilarMovies) {
                    recomQueue.offer(curr);
                } else {
                    if (recomQueue.peek().getRating() < curr.getRating()) {
                        recomQueue.poll();
                        recomQueue.offer(curr);
                    }
                }
            }
            for (Movie child: curr.getSimilarMovies()) {
                if (!visited.get(child.getId())) queue.offer(child);
            }
        }

        while(!recomQueue.isEmpty()) {
            recoms.add(recomQueue.poll());
        }

        return recoms;
    }

	@Override
	public String toString() {
		return "Movie(" + movieId + "," + rating + ")";
	}

    public static void main(String args[]) {
        Movie m1 = new Movie(1, 2.3f);
        Movie m2 = new Movie(2, 3f);
        Movie m3 = new Movie(3, 2.6f);
        Movie m4 = new Movie(4, 2f);
        Movie m5 = new Movie(5, 4f);
        Movie m6 = new Movie(6, 4.5f);
        m1.addSimilarMovie(m3);
        m3.addSimilarMovie(m2);
        m2.addSimilarMovie(m4);
        m2.addSimilarMovie(m5);
        m1.addSimilarMovie(m6);
        List<Movie> expected = new ArrayList<Movie>();
        expected.add(m4);
        expected.add(m3);
        expected.add(m2);
        expected.add(m5);
        expected.add(m6);
        test(m1, 10, expected);
        expected.clear();
        expected.add(m4);
        expected.add(m3);
        expected.add(m2);
        expected.add(m5);
        expected.add(m6);
        test(m1, 5, expected);
        expected.clear();
        expected.add(m2);
        expected.add(m5);
        expected.add(m6);
        test(m1, 3, expected);
        expected.clear();
        expected.add(m6);
        test(m1, 1, expected);
        expected.clear();
        expected.add(m4);
        expected.add(m1);
        expected.add(m3);
        expected.add(m2);
        expected.add(m6);
        test(m5, 10, expected);
        expected.clear();
        expected.add(m4);
        expected.add(m1);
        expected.add(m3);
        expected.add(m2);
        expected.add(m5);
        test(m6, 10, expected);
        expected.clear();
        expected.add(m3);
        expected.add(m2);
        expected.add(m5);
        test(m6, 3, expected);
        expected.clear();
        expected.add(m6);
        test(m5, 1, expected);
    }

    static void test(Movie movie, int num, List<Movie> expected) {
        String result = getMovieRecommendations(movie, num).toString();
        System.out.println("Expected: " + expected.toString() + ", your answer: " + result);
        System.out.println(expected.toString().equals(result) ? "Accept" : "Wrong answer");
    }
}
