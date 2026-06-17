@Composable
fun Sample(){
    var text by remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value  = text,
        onValueChange = {newText-> text = newText}
    )
}