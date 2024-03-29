package org.everit.osgi.ds.annotation.core;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import org.everit.osgi.ds.annotation.api.IHappy;
import org.everit.osgi.ds.annotation.api.SimpleMathService;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(name = "SimpleMathService", provide = SimpleMathService.class)
public class SimpleMatchServiceImpl implements SimpleMathService {

    private IHappy happy;

    @Override
    public long add(final long a, final long b) {
        happy.imHappy();
        return a + b;
    }

    @Override
    public long minus(final long a, final long b) {
        happy.imHappy();
        return a - b;
    }

    @Reference(type = '~')
    public void setHappy(final IHappy happy) {
        this.happy = happy;
    }

}
