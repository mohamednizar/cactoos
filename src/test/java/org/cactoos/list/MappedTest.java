/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2019 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.list;

import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.text.TextOf;
import org.cactoos.text.Upper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.llorllale.cactoos.matchers.HasSize;

/**
 * Test case for {@link Mapped}.
 * @since 0.14
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle MagicNumberCheck (500 lines)
 */
public final class MappedTest {

    @Test
    public void behavesAsCollection() {
        MatcherAssert.assertThat(
            "Can't behave as a list",
            new Mapped<Integer, Integer>(
                i -> i + 1,
                new IterableOf<>(-1, 1, 1)
            ),
            new BehavesAsList<>(0)
        );
    }

    @Test
    public void transformsList() throws Exception {
        MatcherAssert.assertThat(
            "Can't transform an iterable",
            new Mapped<String, Text>(
                input -> new Upper(new TextOf(input)),
                new IterableOf<>("hello", "world", "друг")
            ).iterator().next().asString(),
            new IsEqual<>("HELLO")
        );
    }

    @Test
    public void transformsEmptyList() {
        MatcherAssert.assertThat(
            "Can't transform an empty iterable",
            new Mapped<String, Text>(
                input -> new Upper(new TextOf(input)),
                new IterableOf<>()
            ),
            new HasSize(0)
        );
    }

    @Test
    public void string() {
        MatcherAssert.assertThat(
            "Can't convert to string",
            new Mapped<Integer, Integer>(
                x -> x * 2,
                new ListOf<>(1, 2, 3)
            ).toString(),
            new IsEqual<>("[2, 4, 6]")
        );
    }
}
