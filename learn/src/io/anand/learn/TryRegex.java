package io.anand.learn;


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
    private final static String CP                          =
            "/config/devices/entry/vsys/entry\\[@name='vsys1'\\]";

    private final static String CAT_ENTRY_LIST_RE           =
            CP + "/profiles/custom-url-category/entry\\[@name='([^']+)'\\]/list";
    private final static Pattern catEntryList               = Pattern.compile(CAT_ENTRY_LIST_RE);

    private final static String CAT_ENTRY_LIST_MEMBER_RE    =
            CP + "/profiles/custom-url-category/entry\\[@name='([^']+)'\\]/list/member\\[text\\(\\)='([^']+)'\\]";
    private final static Pattern catEntryListMember         = Pattern.compile(CAT_ENTRY_LIST_MEMBER_RE);

    private final static String CAT_ENTRY_RE                =
            CP + "/profiles/custom-url-category/entry\\[@name='([^']+)'\\]";
    private final static Pattern catEntry                   = Pattern.compile(CAT_ENTRY_RE);

    private final static String PROFILE_RE                  =
            CP + "/profiles/url-filtering/entry\\[@name='([^']+)'\\]";
    private final static Pattern profile                    = Pattern.compile(PROFILE_RE);

    private final static String POLICY_RE                   =
            CP + "/rulebase/security/rules/entry\\[@name='([^']+)'\\]";
    private final static Pattern policy                     = Pattern.compile(POLICY_RE);

    private final static String REPORT_RE                   =
            "/config/shared/reports/(.+)";
    private final static Pattern report                     = Pattern.compile(REPORT_RE);

    private final static String REPORT_REQ                  = "reportname=area1-custom-report&key=";
    private final static String SYSTEM_QUERY                = "cmd=<show><system><info></info></system></show>";
    private final static String COMMIT_REQ                  = "type=commit&cmd=<commit></commit>";
    private final static String JOB_QUERY                   = "type=op&cmd=<show><jobs><id>";
    private final static String xpathPrefix                 = "xpath=";

    private final static Pattern xmlNode                    = Pattern.compile("<[^>]+>([^<]+)<.*");

    private final static Pattern memberNode                 = Pattern.compile("(<member>([^<]+)</member>)");
    
    private static void batchRules () {

        String body         = "element=<member>www.zrzxtime.com%2Fhtml%2F2016%2Fandroidkaifa_0913%2F46.html</member><member>www.chinaqzj.com%2Fhtml%2Fa%2Fcp%2Fnews%2F11315.html</member><member>www.058858.net%2Fforum.php%3Fgoto%3Dlastpost%26mod%3Dredirect%26tid%3D10749</member><member>www.activeliving.com.au%2FDropbox%2F</member><member>ademas.sn%2Fwp-admin%2Fbox%2Fbox%2Findex2.php%3FFaXvoNGpf6gJwjMdE8xVT1PitYDBIe3ybl9CrknQO5z2ShW4m0ZRqUHKcsAL7uqF2hzKtMxSk1RWueGVNlOrE075Da3IdjQAsym9UZfJcongXb6CHi8pwP4BTYvL30431378672</member><member>www.haier029.com%2Findex.php%3FFz1591%3D3Bgekn</member><member>www.chinaqzj.com%2Fhtml%2Fa%2Fcp%2Fnews%2F2924.html</member><member>www.tjszszy.com%2Fimages%2Fpic%2Fhtml%2Fnew.jsp%3F366597</member><member>wwwmucom.aykj.yn.cn%2Ffront%2Fnete7.jsp%3FXm2L%2FWF3YDi.html</member><member>www.hnxhf999.com%2Fnete4.php%3F%3Decgzsz8qyo</member><member>infighter.co.uk%2Finfodropboxdoc%2Fdropbox%2F</member><member>zfxf.com%2Fnews%2F2%2F121180126.html</member><member>www.americandreamhandyman.com%2Fgoogle%2FEd%2FEd%2Findex.php</member><member>sh-foresthotel.com%2Fbb-yye.php%3FeTjVv%3D6EMDy5s54</member><member>tlig.biuro-liketravel.pl%2Fbb-yyw.php%3FBld3zr.html</member><member>www.lemi88.com%2Fnews%2Faap2.php%3Fkzhhw%3Drzhs7</member><member>pe25.www448.com%2Fdandong%2Fwebgame%2Fab%2F0227381.html</member><member>www.liuge.org%2F3ds%2Fsn%2F5357.html</member><member>maven-aviation.com%2Ffrenzy%2Fmo%2Fmail.htm%3F_pageLabel%3Dpage_logonform%26amp%3Bcmd%3DLOB%3DRBGLogon%26amp%3Bsecured_page%3D</member><member>www.liuge.org%2F3ds%2Fsn%2F25650.html</member><member>islingtonbloordentist.com%2Fsent%2Fmeant%2Fyahoo%2Fpng%2F21da305a9da5ee604b4d93e53bc2a04a%2Flogin.php</member><member>www.058858.net%2Fforum.php%3Fextra%3D%26mod%3Dviewthread%26ordertype%3D1%26tid%3D9593</member><member>www.chinaqzj.com%2Fhtml%2Fa%2Fcp%2Fnews%2F4848.html</member><member>www.hifashion.cc%2Fweb%2Fdata%2Fdocument%2Fc51087e98ed6c7424033016a3cb66922%2F</member><member>www.xinma-tools.com%2Fbencandy.php%3Ffid%3D32%26id%3D549</member><member>sxfanwen.com%2Finfo33.aspx</member><member>www.hualyy.com%2Fsonmaj%2F</member><member>3designcenter.com%2Fblog%2Fwp-admin%2Fnetwork%2Fother%2Findex.html%3Famp%25252525252525253Bamp%26amp%3Bamp%3Bamp%3Bamp%3Bamp%3Bamp%3Bcustomerid-2096493259welovesmallhotels.com%25252525252525252F_working_backup%25252525252525252Fwp-content%25252525252525252F</member><member>www.liuge.org%2F3ds%2Fsn%2F1306.html</member><member>hysyz.com%2FItem%2F2342.aspx</member><member>wed-88.com%2Fhtml%2F2uibye.php%3Fb3wj2%2FLOeT7.html</member><member>www.smbsecurity.ro%2FArch%2FArch%2FArchive%2F</member><member>tlf31.dhkkf.com%2Fcv1pn%2F20170425%2F4183279738.html</member><member>www.7t3v9.mhkkb.com%2Fu90x8%2F20170422%2F6032758168.html</member><member>abhinyascs.com%2Fab%2Fdpbx%2F</member><member>szgoldtai.com%2Ftou.asp%3F7cp%2Ff.gov.cn</member><member>geminiautomobiles.com%2Fadmin%2Fadmin%2FHome%2Findex.php</member><member>manchesterhighschool.org%2Fjupgrade%2Fincludes%2Fexcel%2Fexcel.php</member><member>cubelane.com%2Findex-6.php%3Ft915C.html</member><member>xjjinlong.com%2Fnews%2Findex.php%3Fv155%2FFDL7.html</member><member>www.cigars-now.com%2Fall-cigar-samplers%2Fnatural-412</member><member>www.cpwht.com%2Fplus%2Fab%2F03032941.html</member><member>www.mountdigit.net%2Fsite%2Falibaba%2Findex.php%3Femail%3Dabuse%40fangri.com</member><member>www.5166.tv%2Fhtml%2Fnews%2F1384.html</member><member>www.7une.com%2Fnxby%2Ftty%2F242.html</member><member>www.csmes.org%2Fhtml%2F93528.html</member><member>www.szkeliled.com%2Fjujie.asp%3Fn8mcIqAe%2FjLYA.html</member><member>www.chinaqzj.com%2Fhtml%2Fa%2Fcp%2Fnews%2F11272.html</member><member>www.rdcamisas.com.br%2Fshell%2FAccess%2FDocuments%2FHome%2Findex.php</member><member>illnessandabsenteeism.com%2Fwp-includes%2FSimplePie%2FHTTP%2Fwebapps%2Fc2559%2Fwebsrc%3Fcmd%3D_login-submit%26amp%3Bdispatch%3D22aaa8acad783019e8a1299cbd18c0afd8271bdccf76b5a3c9690e425d8351b388b4d040</member>";
        
        Matcher mx  = memberNode.matcher(body);
        int i       = 0;
        while (mx.find()) {
            String rule = mx.group(2);
            System.out.println("(" + i++ + "): rule: " + rule);
        } // All batched entries
    }
    
    private static void DeleteRule () {

        String body         = "/config/devices/entry/vsys/entry[@name='vsys1']/profiles/custom-url-category/entry[@name='A1S-Indicators']/list/member[text()='www.chinalyjz.com%2Fbbyy6.asp%3F1UR%3DVz']";
        
        Matcher mx   = catEntryListMember.matcher(body);
        int i        = 0;

        if (mx.find()) {
            System.out.println("Processing category element member match");
            String category = mx.group(1);
            String rule     = mx.group(2);
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
        batchRules();
        DeleteRule();
    }
}
