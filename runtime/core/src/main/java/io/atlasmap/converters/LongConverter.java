/**
 * Copyright (C) 2017 Red Hat, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.converters;

import io.atlasmap.api.AtlasConversionException;
import io.atlasmap.api.AtlasUnsupportedException;
import io.atlasmap.spi.AtlasConversionConcern;
import io.atlasmap.spi.AtlasConversionInfo;
import io.atlasmap.spi.AtlasPrimitiveConverter;
import io.atlasmap.v2.FieldType;

public class LongConverter implements AtlasPrimitiveConverter<Long> {

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.BOOLEAN, concerns = AtlasConversionConcern.CONVENTION)
    public Boolean convertToBoolean(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value == 0L) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     * @throws AtlasUnsupportedException
     */
    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.BYTE, concerns = AtlasConversionConcern.RANGE)
    public Byte convertToByte(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            return value.byteValue();
        } else {
            throw new AtlasConversionException(new AtlasUnsupportedException(
                    String.format("Long %s is greater than Byte.MAX_VALUE or less than Byte.MIN_VALUE", value)));
        }
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.CHAR, concerns = {
            AtlasConversionConcern.RANGE, AtlasConversionConcern.CONVENTION })
    public Character convertToCharacter(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }

        if (value < Character.MIN_VALUE || value > Character.MAX_VALUE) {
            throw new AtlasConversionException(String
                    .format("Long %s is greater than Character.MAX_VALUE  or less than Character.MIN_VALUE", value));
        }

        return Character.valueOf((char) value.intValue());
    }

    /**
     * @param value
     * @return
     * @throws AtlasConversionException
     */
    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.DOUBLE)
    public Double convertToDouble(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.doubleValue();
    }

    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.FLOAT)
    public Float convertToFloat(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return value.floatValue();
    }

    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.INTEGER, concerns = AtlasConversionConcern.RANGE)
    public Integer convertToInteger(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            throw new AtlasConversionException(
                    String.format("Long %s is greater than Integer.MAX_VALUE  or less than Integer.MIN_VALUE", value));
        }
        return value.intValue();
    }

    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.LONG)
    public Long convertToLong(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        // we want a copy of value
        return new Long(value);
    }

    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.SHORT, concerns = AtlasConversionConcern.RANGE)
    public Short convertToShort(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE) {
            throw new AtlasConversionException(
                    String.format("Long %s is greater than Short.MAX_VALUE or less than Short.MIN_VALUE", value));
        }
        return value.shortValue();
    }

    @Override
    @AtlasConversionInfo(sourceType = FieldType.LONG, targetType = FieldType.STRING)
    public String convertToString(Long value) throws AtlasConversionException {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}
