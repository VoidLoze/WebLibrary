package WebLibrary.WebLibrary.exeption;

public class ReaderNotFoundException extends RuntimeException{

    public ReaderNotFoundException(){
        super("Reader not found");
    }
}
