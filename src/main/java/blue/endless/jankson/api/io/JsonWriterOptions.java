package blue.endless.jankson.api.io;

import java.util.EnumSet;

import blue.endless.jankson.api.Marshaller;
import blue.endless.jankson.impl.MarshallerImpl;

@SuppressWarnings("deprecation")
public class JsonWriterOptions {
	public static JsonWriterOptions DEFAULTS = new JsonWriterOptions();
	public static JsonWriterOptions INI_SON = new JsonWriterOptions(Hint.BARE_ROOT_OBJECT, Hint.KEY_EQUALS_VALUE, Hint.UNQUOTED_KEYS, Hint.OMIT_COMMAS);
	
	private final EnumSet<Hint> hints = EnumSet.noneOf(Hint.class);
	private final Marshaller marshaller;
	
	public JsonWriterOptions(Hint... hints) {
		for(Hint hint : hints) this.hints.add(hint);
		this.marshaller = MarshallerImpl.getFallback();
	}
	
	public JsonWriterOptions(Marshaller marshaller, Hint... hints) {
		for(Hint hint : hints) this.hints.add(hint);
		this.marshaller = marshaller;
	}
	
	public boolean get(Hint hint) {
		return hints.contains(hint);
	}
	
	public static enum Hint {
		/**
		 * If the root element is an object, omit the curly braces around it.
		 */
		BARE_ROOT_OBJECT,
		/**
		 * Do not quote keys unless they contain spaces or other special characters that would make them ambiguous.
		 */
		UNQUOTED_KEYS,
		/**
		 * Write key=value instead of key:value.
		 */
		KEY_EQUALS_VALUE,
		/**
		 * Don't write commas between elements that don't need them.
		 */
		OMIT_COMMAS;
	}
}
