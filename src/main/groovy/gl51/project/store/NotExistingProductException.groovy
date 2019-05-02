package gl51.project.store

class NotExistingProductException extends Exception {
    NotExistingProductException(){
        super()
    }

    NotExistingProductException(String message){
        super(message)
    }
}