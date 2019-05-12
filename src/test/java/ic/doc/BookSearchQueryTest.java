package ic.doc;

import static ic.doc.BuilderBookSearchQuery.aBook;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.sun.source.tree.LambdaExpressionTree.BodyKind;
import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.LibraryCatalogues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BookSearchQueryTest {

  private static final List<Book> BOOKS = Arrays.asList(new Book("Ho ho ho", "Charles dickens", 1980));

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();

  LibraryCatalogues catalogue = context.mock(LibraryCatalogues.class);

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    context.checking(new Expectations() {
      {
        exactly(1).of(catalogue).searchFor("LASTNAME='dickens' ");
        will(returnValue(BOOKS));
      }
    });

    List<Book> books = aBook().withName2("dickens").build().execute(catalogue);

    assertThat(books, is(BOOKS));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    context.checking(new Expectations() {
      {
        exactly(1).of(catalogue).searchFor("FIRSTNAME='Jane' ");
      }
    });

    aBook().withName1("Jane").build().execute(catalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    List<Book> books = aBook().withTitle("Two Cities").build().execute(catalogue);


    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    List<Book> books = aBook().withDate2(1700).build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    List<Book> books = aBook().withDate1(1950).build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = aBook().withName1("dickens").withDate2(1840).build().execute(catalogue);

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = aBook().withTitle("of").withDate1(1800).withDate2(2000).build().execute(catalogue);

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
