package ic.doc.catalogues;

import ic.doc.Book;
import java.util.List;

public interface LibraryCatalogues {

  List<Book> searchFor(String query);

}
