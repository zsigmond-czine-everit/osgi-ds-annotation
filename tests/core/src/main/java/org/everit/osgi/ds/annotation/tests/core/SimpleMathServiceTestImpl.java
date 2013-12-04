package org.everit.osgi.ds.annotation.tests.core;

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

import junit.framework.Assert;

import org.everit.osgi.ds.annotation.api.SimpleMathService;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(name = "SimpleMathServiceTest", provide = SimpleMathServiceTest.class, properties = "osgitest=junit4")
public class SimpleMathServiceTestImpl implements SimpleMathServiceTest {

    private SimpleMathService simpleMathService;

    private static long A = 10L;

    private static long B = 2L;

    private static long A_PLUS_B = 12L;

    private static long A_MINUS_B = 8L;

    @Reference(type = '~')
    public void setSimpleMathService(final SimpleMathService simpleMathService) {
        this.simpleMathService = simpleMathService;
    }

    @Override
    public void testAdd() {
        long add = simpleMathService.add(A, B);
        Assert.assertEquals(A_PLUS_B, add);
    }

    @Override
    public void testMinus() {
        long minus = simpleMathService.minus(A, B);
        Assert.assertEquals(A_MINUS_B, minus);
    }

}
