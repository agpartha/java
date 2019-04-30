package io.anand.play;


import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Regex parser
 */
public class TryRegex
{
    private final static String GET_ACTION                  = "action=get";
    private final static String SET_ACTION                  = "set";
    private final static String DELETE_ACTION               = "delete";

    private final static String GET_ACTION_KV               = "action=get";
    private final static String SET_ACTION_KV               = "action=set";
    private final static String DELETE_ACTION_KV            = "action=delete";
    //common prefix
    private final static String RE                          =
            "/root/elements/single/org/single\\[@label='org'\\]";

    private final static String TEMPLATE_GROUP_ENTRY_RE     =
            RE + "/templates/group/entry\\[@label='([^']+)'\\]/list";
    private final static Pattern tempGroupEntry             = Pattern.compile(TEMPLATE_GROUP_ENTRY_RE);

    private final static String TEMPLATE_GROUP_ENTRY_LIST_MEMBER_RE    =
            RE + "/templates/group/entry\\[@label='([^']+)'\\]/list/member\\[text\\(\\)='([^']+)'\\]";
    private final static Pattern tempGroupListMember        = Pattern.compile(TEMPLATE_GROUP_ENTRY_LIST_MEMBER_RE);

    private final static String TEMPLATE_GROUP_RE           =
            RE + "/templates/group/entry\\[@label='([^']+)'\\]";
    private final static Pattern tempEntry                  = Pattern.compile(TEMPLATE_GROUP_RE);

    private final static String TEMPLATE_RE                 =
            RE + "/templates/selected/value\\[@label='([^']+)'\\]";
    private final static Pattern temp                       = Pattern.compile(TEMPLATE_RE);

    private final static String CHECK_RE                    =
            RE + "/home/check/info/value\\[@label='([^']+)'\\]";
    private final static Pattern check                      = Pattern.compile(CHECK_RE);

    private final static String DATA_RE                     =
            "/root/common/data/(.+)";
    private final static Pattern report                     = Pattern.compile(DATA_RE);

    private final static String OUTPUT_REQ                  = "output=comp-my-list&cha=";
    private final static String SYSTEM_QUERY                = "op=<get><sys><info></info></sys></view>";
    private final static String CONFIRM_REQ                 = "type=confirm&op=<confirm></confirm>";
    private final static String PROC_QUERY                  = "type=op&cmd=<show><processes><id>";
    private final static String xpathPrefix                 = "xpath=";

    private final static Pattern xmlNode                    = Pattern.compile("<[^>]+>([^<]+)<.*");

    private final static Pattern memberNode                 = Pattern.compile("(<member>([^<]+)</member>)");
    
    private static void listElements () {
      String body         = "element=<member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.google.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member><member>www.intel.com</member>";
      
        Matcher mx  = memberNode.matcher(body);
        int i       = 0;
        while (mx.find()) {
            String rule = mx.group(2);
            System.out.println("(" + i++ + "): rule: " + rule);
        } // All batched entries
    }
    
    private static void RemoveElement () {
        String body         = "/root/elements/single/org/single[@label='org']/templates/group/entry[@label='myname']/list/member[text()='www.apple.com']";
        Matcher mx   = tempGroupListMember.matcher(body);
        int i        = 0;

        if (mx.find()) {
            System.out.println("Processing category element member match");
            String group = mx.group(1);
            String element     = mx.group(2);
            System.out.println("Processing category element member match, group 1 match: " +
                    mx.group(1) + ", group 2 match: " + mx.group(2));
        }
        
        mx.reset();

        while (mx.find()) {
            String rule = mx.group(2);
            System.out.println("(" + i++ + "): rule: " + rule);
        } // All batched entries
        

    }
    
    public static void main(String[] args) {
        System.out.println("Hello RegEx");
        listElements();
        RemoveElement();
    }
}
