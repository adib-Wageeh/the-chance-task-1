
fun check(text:String,result:Boolean,correctResult:Boolean){
    if(result == correctResult){
        println("Correct: $text")
    }else{
        println("False: $text")
    }
}