package org.rancore.javadoc;

/**
 * <h1>Heading H1</h1>
 * <h2>Heading H2</h2>
 * <h3>Heading H3</h3>
 * <p>This class represents a simple example of using HTML tags in JavaDocs.</p>
 * <p>
 * <b>bold</b>
 * <i>italic</i>
 * <u>underline</u>
 * </p>
 */
class Template {

    /**
     * <p><u><b>Class name</b></u></p>
     * <p>LoremClass</p>
     * <p><u><b>Description</b></u></p>
     * <p>Lorem ipsum dolor sit amet</p>
     * <p><u><b>Usage</b></u></p>
     * <p>java LoremClass <directory> [--print-structure | -p] [--save-to-file | -s <output-path>]</p>
     */
    private static class LoremClass {}

    /**
     * <p>Block of preformatted text:</p>
     * <pre>
     * public static void main(String[] args) {
     *     System.out.println("Hello, world!");
     * }
     * </pre>
     */
    private static void preformattedText(){}

    /**
     * <a href="www.github.com/amaldoror">Link</a>
     */
    private static void link(){}

    /**
     * <code>inline code</code>
     */
    private static void inlineCode(){}

    /**
     * <p>Unordered list:</p>
     * <ul>
     *   <li>Item Lorem</li>
     *   <li>Item Ipsum</li>
     *   <li>Item Dolor</li>
     * </ul>
     */
    private static void unorderedList(){}

    /**
     * <p>Ordered list:</p>
     * <ol>
     *     <li>Item</li>
     *     <li>Item</li>
     *     <li>Item</li>
     * </ol>
     */
    private static void orderedList(){}

    /**
     * <p>Description list:</p>
     * <dl>
     *     <dt>Item 1</dt>
     *     <dd>Description</dd>
     *     <dt>Item 2</dt>
     *     <dd>Description</dd>
     * </dl>
     */
    private static void descriptionList(){}

    /**
     * <table>
     *  <th>Table Header</th>
     *  <thead>Table Head</thead>
     *      <tr>Row 1</tr>
     *          <td>Cell 1.1</td>
     *          <td>Cell 1.2</td>
     *      <tr>Row 2</tr>
     *          <td>Cell 2.1</td>
     *          <td>Cell 2.2</td>
     *  <tbody>Table Body</tbody>
     *      <tr>Row 1</tr>
     *          <td>Cell 1.1</td>
     *          <td>Cell 1.2</td>
     *      <tr>Row 2</tr>
     *          <td>Cell 2.1</td>
     *          <td>Cell 2.2</td>
     *  <tfoot>Table Footer</tfoot>
     *      <tr>Row 1</tr>
     *          <td>Cell 1.1</td>
     *          <td>Cell 1.2</td>
     *      <tr>Row 2</tr>
     *          <td>Cell 2.1</td>
     *          <td>Cell 2.2</td>
     * </table>
     */
    private static void table(){}
}
