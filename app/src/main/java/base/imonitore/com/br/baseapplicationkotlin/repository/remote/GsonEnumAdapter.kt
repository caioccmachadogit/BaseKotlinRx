package base.imonitore.com.br.baseapplicationkotlin.repository.remote

import com.google.gson.*
import java.lang.reflect.Type

class GsonEnumAdapter : JsonSerializer<Enum<*>>, JsonDeserializer<Enum<*>> {

    override fun serialize(enumerator: Enum<*>, type: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(enumerator.ordinal)
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type, context: JsonDeserializationContext): Enum<*>? {

        //Log.i("GsonEnumAdapter", "deserialize type" + type.toString());

        /*Class<Enum<?>> clazz = (Class<Enum<?>>) type.getClass();
		Enum<?>[] values = clazz.getEnumConstants();*/


        //		Enum<?> elements = null;
        //
        //


        //		if(index > 0) index--;
        //		else 		  index = 0;
        //
        //		/*return values[index];*/


//        if (type === FormatoMidiaEnum::class.java)
//            return FormatoMidiaEnum.values()[json.asInt]
//
//        if (type === TipoMidiaEnum::class.java)
//            return TipoMidiaEnum.values()[json.asInt]
//
//        if (type === DigitalEnum::class.java)
//            return DigitalEnum.values()[json.asInt]
//
//        if (type === PerfilUserEnum::class.java) {
//            val index = json.asInt - 1
//            return PerfilUserEnum.values()[index]
//        }


        //        if (type == FormatoMidiaEnum.class)
        //            return FormatoMidiaEnum.values()[json.getAsInt()];
        //
        //        if (type == DigitalEnum.class)
        //            return DigitalEnum.values()[json.getAsInt()];

        //		if (type == TipoMidiaEnum.class)
        //			return  TipoMidiaEnum.values()[index];

        //		if (type == CategoriaCNHEnum.class)
        //			return  CategoriaCNHEnum.values()[index];
        //
        //		if (type == TipoAlertaEnum.class)
        //			return  TipoAlertaEnum.values()[index];
        //
        //		if (type == StatusAgendaEnum.class)
        //			return  StatusAgendaEnum.values()[index];
        //
        //		if (type == GravidadeInfracaoEnum.class)
        //			return  GravidadeInfracaoEnum.values()[index];
        //
        //		if (type == DigitalEnum.class){
        //			elements = DigitalEnum.values()[index];
        ////			Log.i("Biometria","Nome : "+elements.name()+"Ordem:"+elements.ordinal());
        //			return  elements;
        //		}
        //
        //		if (type == TelemetriaEnum.class)
        //			return  TelemetriaEnum.values()[index];
        //
        //		if (type == TipoViaEnum.class)
        //			return  TipoViaEnum.values()[index];
        //
        //        if (type == TipoOcorrenciaEnum.class)
        //            return  TipoOcorrenciaEnum.values()[index];
        //
        //        if (type == TipoBalizaEnum.class)
        //            return  TipoBalizaEnum.values()[index];
        //
        //		if (type == StatusAprovacaoEnum.class)
        //			return  StatusAprovacaoEnum.values()[index];
        //
        //
        //		if (type == StatusMidia.class)
        //			return  StatusMidia.values()[index];
        //
        //		if (type == FaltasEnum.class)
        //			return  FaltasEnum.values()[index];

        return null
    }
}
