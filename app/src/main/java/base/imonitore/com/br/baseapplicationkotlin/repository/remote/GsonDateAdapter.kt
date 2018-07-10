package base.imonitore.com.br.baseapplicationkotlin.repository.remote

import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GsonDateAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    private val dateFormatSaida = SimpleDateFormat("yyyyMMddHHmmss")

    override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        //return new JsonPrimitive(src.getTime());
        val dateFormatted = dateFormatSaida.format(src)
        //BatLog.i("GsonDateAdapter", "Data formatada: "+dateFormatted);
        return JsonPrimitive(dateFormatted)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {

        //return new Date(json.getAsJsonPrimitive().getAsLong());

        var date: Date? = null

        try {
            date = dateFormat.parse(json.asString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return date

    }
}
