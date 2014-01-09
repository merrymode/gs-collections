/*
 * Copyright 2011 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.block.factory;

import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.primitive.BooleanFunction;
import com.gs.collections.api.block.function.primitive.ByteFunction;
import com.gs.collections.api.block.function.primitive.CharFunction;
import com.gs.collections.api.block.function.primitive.DoubleFunction;
import com.gs.collections.api.block.function.primitive.FloatFunction;
import com.gs.collections.api.block.function.primitive.IntFunction;
import com.gs.collections.api.block.function.primitive.LongFunction;
import com.gs.collections.api.block.function.primitive.ShortFunction;
import com.gs.collections.impl.block.function.primitive.IntegerFunctionImpl;

public final class StringFunctions
{
    private static final Function<String, String> TO_UPPER_CASE = new ToUpperCaseFunction();
    private static final Function<String, String> TO_LOWER_CASE = new ToLowerCaseFunction();
    private static final IntegerFunctionImpl<String> LENGTH = new LengthFunction();
    private static final Function<String, String> TRIM = new TrimFunction();
    private static final Function<String, Character> FIRST_LETTER = new FirstLetterFunction();
    private static final Function<String, Integer> TO_INTEGER = new ToIntegerFunction();

    private static final BooleanFunction<String> TO_PRIMITIVE_BOOLEAN = new ToPrimitiveBooleanFunction();
    private static final ByteFunction<String> TO_PRIMITIVE_BYTE = new ToPrimitiveByteFunction();
    private static final CharFunction<String> TO_PRIMITIVE_CHAR = new ToPrimitiveCharFunction();
    private static final CharFunction<String> TO_FIRST_CHAR = new ToFirstCharFunction();
    private static final DoubleFunction<String> TO_PRIMITIVE_DOUBLE = new ToPrimitiveDoubleFunction();
    private static final FloatFunction<String> TO_PRIMITIVE_FLOAT = new ToPrimitiveFloatFunction();
    private static final IntFunction<String> TO_PRIMITIVE_INT = new ToPrimitiveIntFunction();
    private static final LongFunction<String> TO_PRIMITIVE_LONG = new ToPrimitiveLongFunction();
    private static final ShortFunction<String> TO_PRIMITIVE_SHORT = new ToPrimitiveShortFunction();

    private StringFunctions()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static Function<String, String> toUpperCase()
    {
        return TO_UPPER_CASE;
    }

    public static Function<String, String> toLowerCase()
    {
        return TO_LOWER_CASE;
    }

    public static Function<String, Integer> toInteger()
    {
        return TO_INTEGER;
    }

    public static BooleanFunction<String> toPrimitiveBoolean()
    {
        return TO_PRIMITIVE_BOOLEAN;
    }

    public static ByteFunction<String> toPrimitiveByte()
    {
        return TO_PRIMITIVE_BYTE;
    }

    public static CharFunction<String> toPrimitiveChar()
    {
        return TO_PRIMITIVE_CHAR;
    }

    /**
     * @throws StringIndexOutOfBoundsException if the String is empty
     */
    public static CharFunction<String> toFirstChar()
    {
        return TO_FIRST_CHAR;
    }

    public static DoubleFunction<String> toPrimitiveDouble()
    {
        return TO_PRIMITIVE_DOUBLE;
    }

    public static FloatFunction<String> toPrimitiveFloat()
    {
        return TO_PRIMITIVE_FLOAT;
    }

    public static IntFunction<String> toPrimitiveInt()
    {
        return TO_PRIMITIVE_INT;
    }

    public static LongFunction<String> toPrimitiveLong()
    {
        return TO_PRIMITIVE_LONG;
    }

    public static ShortFunction<String> toPrimitiveShort()
    {
        return TO_PRIMITIVE_SHORT;
    }

    public static IntegerFunctionImpl<String> length()
    {
        return LENGTH;
    }

    public static Function<String, Character> firstLetter()
    {
        return FIRST_LETTER;
    }

    public static Function<String, String> subString(int beginIndex, int endIndex)
    {
        return new SubStringFunction(beginIndex, endIndex);
    }

    /**
     * Returns a function that returns a copy of a {@link String}, with leading and trailing whitespace
     * omitted.
     *
     * @see String#trim()
     */
    public static Function<String, String> trim()
    {
        return TRIM;
    }

    public static Function<String, String> append(String valueToAppend)
    {
        return new AppendFunction(valueToAppend);
    }

    public static Function<String, String> prepend(String valueToPrepend)
    {
        return new PrependFunction(valueToPrepend);
    }

    private static final class ToUpperCaseFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        public String valueOf(String object)
        {
            return object.toUpperCase();
        }

        @Override
        public String toString()
        {
            return "string.toUpperCase()";
        }
    }

    private static final class ToLowerCaseFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        public String valueOf(String object)
        {
            return object.toLowerCase();
        }

        @Override
        public String toString()
        {
            return "string.toLowerCase()";
        }
    }

    private static final class LengthFunction extends IntegerFunctionImpl<String>
    {
        private static final long serialVersionUID = 1L;

        public int intValueOf(String string)
        {
            return string.length();
        }

        @Override
        public String toString()
        {
            return "string.length()";
        }
    }

    private static final class TrimFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        public String valueOf(String string)
        {
            return string.trim();
        }

        @Override
        public String toString()
        {
            return "string.trim()";
        }
    }

    private static final class FirstLetterFunction
            implements Function<String, Character>
    {
        private static final long serialVersionUID = 1L;

        public Character valueOf(String object)
        {
            return object == null || object.length() < 1 ? null : object.charAt(0);
        }
    }

    private static final class ToIntegerFunction implements Function<String, Integer>
    {
        private static final long serialVersionUID = 1L;

        public Integer valueOf(String string)
        {
            return Integer.valueOf(string);
        }
    }

    private static final class ToPrimitiveBooleanFunction implements BooleanFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public boolean booleanValueOf(String string)
        {
            return Boolean.parseBoolean(string);
        }
    }

    private static final class ToPrimitiveByteFunction implements ByteFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public byte byteValueOf(String string)
        {
            return Byte.parseByte(string);
        }
    }

    private static final class ToFirstCharFunction implements CharFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public char charValueOf(String string)
        {
            return string.charAt(0);
        }
    }

    private static final class ToPrimitiveCharFunction implements CharFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public char charValueOf(String string)
        {
            return (char) Integer.parseInt(string);
        }
    }

    private static final class ToPrimitiveDoubleFunction implements DoubleFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public double doubleValueOf(String string)
        {
            return Double.parseDouble(string);
        }
    }

    private static final class ToPrimitiveFloatFunction implements FloatFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public float floatValueOf(String string)
        {
            return Float.parseFloat(string);
        }
    }

    private static final class ToPrimitiveIntFunction implements IntFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public int intValueOf(String string)
        {
            return Integer.parseInt(string);
        }
    }

    private static final class ToPrimitiveLongFunction implements LongFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public long longValueOf(String string)
        {
            return Long.parseLong(string);
        }
    }

    private static final class ToPrimitiveShortFunction implements ShortFunction<String>
    {
        private static final long serialVersionUID = 1L;

        public short shortValueOf(String string)
        {
            return Short.parseShort(string);
        }
    }

    private static final class SubStringFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        private final int beginIndex;
        private final int endIndex;

        private SubStringFunction(int beginIndex, int endIndex)
        {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        public String valueOf(String string)
        {
            return string.substring(this.beginIndex, this.endIndex);
        }

        @Override
        public String toString()
        {
            return "string.subString(" + this.beginIndex + ',' + this.endIndex + ')';
        }
    }

    private static final class AppendFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        private final String valueToAppend;

        private AppendFunction(String valueToAppend)
        {
            this.valueToAppend = valueToAppend;
        }

        public String valueOf(String string)
        {
            return string + this.valueToAppend;
        }
    }

    private static final class PrependFunction implements Function<String, String>
    {
        private static final long serialVersionUID = 1L;

        private final String valueToPrepend;

        private PrependFunction(String valueToPrepend)
        {
            this.valueToPrepend = valueToPrepend;
        }

        public String valueOf(String string)
        {
            return this.valueToPrepend + string;
        }
    }
}
