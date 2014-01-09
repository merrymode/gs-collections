/*
 * Copyright 2013 Goldman Sachs.
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

package com.gs.collections.api.list;

import java.util.List;
import java.util.ListIterator;

import com.gs.collections.api.LazyIterable;
import com.gs.collections.api.RichIterable;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.function.Function2;
import com.gs.collections.api.block.function.primitive.BooleanFunction;
import com.gs.collections.api.block.function.primitive.ByteFunction;
import com.gs.collections.api.block.function.primitive.CharFunction;
import com.gs.collections.api.block.function.primitive.DoubleFunction;
import com.gs.collections.api.block.function.primitive.FloatFunction;
import com.gs.collections.api.block.function.primitive.IntFunction;
import com.gs.collections.api.block.function.primitive.LongFunction;
import com.gs.collections.api.block.function.primitive.ShortFunction;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.predicate.Predicate2;
import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.block.procedure.primitive.ObjectIntProcedure;
import com.gs.collections.api.list.primitive.BooleanList;
import com.gs.collections.api.list.primitive.ByteList;
import com.gs.collections.api.list.primitive.CharList;
import com.gs.collections.api.list.primitive.DoubleList;
import com.gs.collections.api.list.primitive.FloatList;
import com.gs.collections.api.list.primitive.IntList;
import com.gs.collections.api.list.primitive.LongList;
import com.gs.collections.api.list.primitive.ShortList;
import com.gs.collections.api.multimap.list.ListMultimap;
import com.gs.collections.api.partition.list.PartitionList;
import com.gs.collections.api.stack.MutableStack;
import com.gs.collections.api.tuple.Pair;

/**
 * An iterable whose items are ordered and may be accessed directly by index.  A reverseForEach
 * internal iterator is available iterating over the indexed iterable in reverse, starting from
 * the end and going to the beginning.  Additionally, internal iterators are available for batching
 * style iteration which is useful for parallel processing.
 */
public interface ListIterable<T>
        extends RichIterable<T>
{
    /**
     * Iterates over the section of the list covered by the specified inclusive indexes.  The indexes are
     * both inclusive.
     * <p/>
     * <p/>
     * <pre>e.g.
     * ListIterable<People> people = FastList.newListWith(ted, mary, bob, sally)
     * people.forEach(0, 1, new Procedure<Person>()
     * {
     *     public void value(Person person)
     *     {
     *          LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     * <p/>
     * This code would output ted and mary's names.
     */
    void forEach(int startIndex, int endIndex, Procedure<? super T> procedure);

    /**
     * Iterates over the section of the list covered by the specified inclusive indexes.  The indexes are
     * both inclusive.
     * <p/>
     * <p/>
     * <pre>e.g.
     * ListIterable<People> people = FastList.newListWith(ted, mary, bob, sally)
     * people.forEachWithIndex(0, 1, new ObjectIntProcedure<Person>()
     * {
     *     public void value(Person person, int index)
     *     {
     *          LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     * <p/>
     * This code would output ted and mary's names.
     */
    void forEachWithIndex(int fromIndex, int toIndex, ObjectIntProcedure<? super T> objectIntProcedure);

    /**
     * Evaluates the procedure for each element of the list iterating in reverse order.
     * <p/>
     * <pre>e.g.
     * people.reverseForEach(new Procedure<Person>()
     * {
     *     public void value(Person person)
     *     {
     *         LOGGER.info(person.getName());
     *     }
     * });
     * </pre>
     */
    void reverseForEach(Procedure<? super T> procedure);

    /**
     * Returns the item at the specified position in this list iterable.
     */
    T get(int index);

    /**
     * Returns the index of the first occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified item
     * in this list, or -1 if this list does not contain the item.
     */
    int lastIndexOf(Object o);

    /**
     * Returns the value of the size of this iterable
     */
    int size();

    /**
     * Returns the item at index 0 of the container.  If the container is empty, null is returned.  If null
     * is a valid item of the container, then a developer will need to check to see if the container is
     * empty first.
     */
    T getFirst();

    /**
     * Returns the item at index (size() - 1) of the container.  If the container is empty, null is returned.  If null
     * is a valid item of the container, then a developer will need to check to see if the container is
     * empty first.
     */
    T getLast();

    /**
     * @see List#listIterator()
     * @since 1.0.
     */
    ListIterator<T> listIterator();

    /**
     * @see List#listIterator(int)
     * @since 1.0.
     */
    ListIterator<T> listIterator(int index);

    /**
     * Converts the list to a mutable MutableStack implementation.
     *
     * @since 2.0
     */
    MutableStack<T> toStack();

    ListIterable<T> select(Predicate<? super T> predicate);

    <P> ListIterable<T> selectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    ListIterable<T> reject(Predicate<? super T> predicate);

    <P> ListIterable<T> rejectWith(Predicate2<? super T, ? super P> predicate, P parameter);

    PartitionList<T> partition(Predicate<? super T> predicate);

    <S> ListIterable<S> selectInstancesOf(Class<S> clazz);

    <V> ListIterable<V> collect(Function<? super T, ? extends V> function);

    BooleanList collectBoolean(BooleanFunction<? super T> booleanFunction);

    ByteList collectByte(ByteFunction<? super T> byteFunction);

    CharList collectChar(CharFunction<? super T> charFunction);

    DoubleList collectDouble(DoubleFunction<? super T> doubleFunction);

    FloatList collectFloat(FloatFunction<? super T> floatFunction);

    IntList collectInt(IntFunction<? super T> intFunction);

    LongList collectLong(LongFunction<? super T> longFunction);

    ShortList collectShort(ShortFunction<? super T> shortFunction);

    <P, V> ListIterable<V> collectWith(Function2<? super T, ? super P, ? extends V> function, P parameter);

    <V> ListIterable<V> collectIf(Predicate<? super T> predicate, Function<? super T, ? extends V> function);

    <V> ListIterable<V> flatCollect(Function<? super T, ? extends Iterable<V>> function);

    <V> ListMultimap<V, T> groupBy(Function<? super T, ? extends V> function);

    <V> ListMultimap<V, T> groupByEach(Function<? super T, ? extends Iterable<V>> function);

    /**
     * Returns a new {@code ListIterable} containing the distinct elements in this list.
     * <p/>
     * Conceptually similar to {@link #toSet()}.{@link #toList()} but retains the original order. If an element appears
     * multiple times in this list, the first one will be copied into the result.
     *
     * @return {@code ListIterable} of distinct elements
     * @since 3.0
     */
    ListIterable<T> distinct();

    <S> ListIterable<Pair<T, S>> zip(Iterable<S> that);

    ListIterable<Pair<T, Integer>> zipWithIndex();

    /**
     * Returns the initial elements that satisfy the Predicate. Short circuits at the first element which does not
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    ListIterable<T> takeWhile(Predicate<? super T> predicate);

    /**
     * Returns the final elements that do not satisfy the Predicate. Short circuits at the first element which does
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    ListIterable<T> dropWhile(Predicate<? super T> predicate);

    /**
     * Returns a Partition of the initial elements that satisfy the Predicate and the remaining elements. Short circuits at the first element which does
     * satisfy the Predicate.
     *
     * @since 3.0
     */
    PartitionList<T> partitionWhile(Predicate<? super T> predicate);

    /**
     * Returns a reversed view of this ListIterable.
     *
     * @since 3.0
     */
    LazyIterable<T> asReversed();

    /**
     * Follows the same general contract as {@link List#equals(Object)}.
     */
    @Override
    boolean equals(Object o);

    /**
     * Follows the same general contract as {@link List#hashCode()}.
     */
    @Override
    int hashCode();
}
