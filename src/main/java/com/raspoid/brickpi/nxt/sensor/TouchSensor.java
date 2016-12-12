/*******************************************************************************
 * Copyright (c) 2016 Julien Louette & Gaël Wittorski
 * 
 * This file is part of Raspoid.
 * 
 * Raspoid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Raspoid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Raspoid.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.raspoid.brickpi.nxt.sensor;

import static com.raspoid.brickpi.nxt.sensor.SensorType.TYPE_SENSOR_TOUCH;

/**
 * Implementation of the Touch Sensor.
 * 
 * @author Julien Louette &amp; Ga&euml;l Wittorski
 * @version 1.0
 */
public class TouchSensor extends RawSensor {

    @Override
    public SensorType getType() {
        return TYPE_SENSOR_TOUCH;
    }

    /**
     * Tells if the button is pressed.
     * @return true if the button was pressed, false otherwise
     */
    public boolean isPressed() {
        return getValue() == 1;
    }
}
