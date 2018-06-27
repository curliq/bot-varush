package app.rest.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import app.utils.mapping.MappingUtils;

public class PlayerPOJO {

    @SerializedName("data")
    private ArrayList<Data> data;

    public ArrayList<Data> getData() {
        return data;
    }

    public class Assets {

        @SerializedName("data")
        private List<Object> data = null;

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

    }

    public class Attributes {

        @SerializedName("name")
        private String name;
        @SerializedName("patchVersion")
        private String patchVersion;
        @SerializedName("shardId")
        private String shardId;
        @SerializedName("stats")
        private Stats stats = new Stats();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatchVersion() {
            return patchVersion;
        }

        public void setPatchVersion(String patchVersion) {
            this.patchVersion = patchVersion;
        }

        public String getShardId() {
            return shardId;
        }

        public void setShardId(String shardId) {
            this.shardId = shardId;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public Stats getStats() {
            return stats;
        }

    }

    /**
     * The player's data stats from the madglory API are named with integers, i.e. "1005", since we can't do that in
     * java nor in sql, we prefix a "c" to every key. "c" can stand for "column"
     */
    public class Stats {
        @SerializedName("picture")
        private String pictureId;
        @SerializedName("title")
        private String titleId;
        @SerializedName("2")
        public Integer c2;
        @SerializedName("3")
        public Integer c3;
        @SerializedName("4")
        public Integer c4;
        @SerializedName("56")
        public Integer c56;
        @SerializedName("1001")
        public Integer c1001;
        @SerializedName("1002")
        public Integer c1002;
        @SerializedName("1003")
        public Integer c1003;
        @SerializedName("1004")
        public Integer c1004;
        @SerializedName("1005")
        public Integer c1005;
        @SerializedName("1006")
        public Integer c1006;
        @SerializedName("1007")
        public Integer c1007;
        @SerializedName("1008")
        public Integer c1008;
        @SerializedName("1009")
        public Integer c1009;
        @SerializedName("1013")
        public Integer c1013;
        @SerializedName("1014")
        public Integer c1014;
        @SerializedName("1015")
        public Integer c1015;
        @SerializedName("1016")
        public Integer c1016;
        @SerializedName("1021")
        public Integer c1021;
        @SerializedName("1101")
        public Integer c1101;
        @SerializedName("1102")
        public Integer c1102;
        @SerializedName("1103")
        public Integer c1103;
        @SerializedName("1104")
        public Integer c1104;
        @SerializedName("1105")
        public Integer c1105;
        @SerializedName("1106")
        public Integer c1106;
        @SerializedName("1107")
        public Integer c1107;
        @SerializedName("1108")
        public Integer c1108;
        @SerializedName("1109")
        public Integer c1109;
        @SerializedName("1113")
        public Integer c1113;
        @SerializedName("1114")
        public Integer c1114;
        @SerializedName("1115")
        public Integer c1115;
        @SerializedName("1116")
        public Integer c1116;
        @SerializedName("1121")
        public Integer c1121;
        @SerializedName("11001")
        public Integer c11001;
        @SerializedName("11002")
        public Integer c11002;
        @SerializedName("11003")
        public Integer c11003;
        @SerializedName("11004")
        public Integer c11004;
        @SerializedName("11005")
        public Integer c11005;
        @SerializedName("11006")
        public Integer c11006;
        @SerializedName("11007")
        public Integer c11007;
        @SerializedName("11008")
        public Integer c11008;
        @SerializedName("11009")
        public Integer c11009;
        @SerializedName("11010")
        public Integer c11010;
        @SerializedName("11011")
        public Integer c11011;
        @SerializedName("11012")
        public Integer c11012;
        @SerializedName("11013")
        public Integer c11013;
        @SerializedName("11014")
        public Integer c11014;
        @SerializedName("11015")
        public Integer c11015;
        @SerializedName("11016")
        public Integer c11016;
        @SerializedName("11017")
        public Integer c11017;
        @SerializedName("11018")
        public Integer c11018;
        @SerializedName("11019")
        public Integer c11019;
        @SerializedName("11020")
        public Integer c11020;
        @SerializedName("11021")
        public Integer c11021;
        @SerializedName("11022")
        public Integer c11022;
        @SerializedName("11023")
        public Integer c11023;
        @SerializedName("11025")
        public Integer c11025;
        @SerializedName("11026")
        public Integer c11026;
        @SerializedName("11027")
        public Integer c11027;
        @SerializedName("11028")
        public Integer c11028;
        @SerializedName("11029")
        public Integer c11029;
        @SerializedName("11030")
        public Integer c11030;
        @SerializedName("11031")
        public Integer c11031;
        @SerializedName("11035")
        public Integer c11035;
        @SerializedName("11038")
        public Integer c11038;
        @SerializedName("11039")
        public Integer c11039;
        @SerializedName("11040")
        public Integer c11040;
        @SerializedName("11041")
        public Integer c11041;
        @SerializedName("11043")
        public Integer c11043;
        @SerializedName("11045")
        public Integer c11045;
        @SerializedName("12001")
        public Integer c12001;
        @SerializedName("12002")
        public Integer c12002;
        @SerializedName("12003")
        public Integer c12003;
        @SerializedName("12004")
        public Integer c12004;
        @SerializedName("12005")
        public Integer c12005;
        @SerializedName("12006")
        public Integer c12006;
        @SerializedName("12007")
        public Integer c12007;
        @SerializedName("12008")
        public Integer c12008;
        @SerializedName("12009")
        public Integer c12009;
        @SerializedName("12010")
        public Integer c12010;
        @SerializedName("12011")
        public Integer c12011;
        @SerializedName("12012")
        public Integer c12012;
        @SerializedName("12013")
        public Integer c12013;
        @SerializedName("12014")
        public Integer c12014;
        @SerializedName("12015")
        public Integer c12015;
        @SerializedName("12016")
        public Integer c12016;
        @SerializedName("12017")
        public Integer c12017;
        @SerializedName("12018")
        public Integer c12018;
        @SerializedName("12019")
        public Integer c12019;
        @SerializedName("12020")
        public Integer c12020;
        @SerializedName("12021")
        public Integer c12021;
        @SerializedName("12022")
        public Integer c12022;
        @SerializedName("12023")
        public Integer c12023;
        @SerializedName("12025")
        public Integer c12025;
        @SerializedName("12026")
        public Integer c12026;
        @SerializedName("12027")
        public Integer c12027;
        @SerializedName("12028")
        public Integer c12028;
        @SerializedName("12029")
        public Integer c12029;
        @SerializedName("12030")
        public Integer c12030;
        @SerializedName("12031")
        public Integer c12031;
        @SerializedName("12035")
        public Integer c12035;
        @SerializedName("12038")
        public Integer c12038;
        @SerializedName("12039")
        public Integer c12039;
        @SerializedName("12040")
        public Integer c12040;
        @SerializedName("12041")
        public Integer c12041;
        @SerializedName("12043")
        public Integer c12043;
        @SerializedName("12045")
        public Integer c12045;
        @SerializedName("13001")
        public Integer c13001;
        @SerializedName("13002")
        public Integer c13002;
        @SerializedName("13003")
        public Integer c13003;
        @SerializedName("13004")
        public Integer c13004;
        @SerializedName("13005")
        public Integer c13005;
        @SerializedName("13006")
        public Integer c13006;
        @SerializedName("13007")
        public Integer c13007;
        @SerializedName("13008")
        public Integer c13008;
        @SerializedName("13009")
        public Integer c13009;
        @SerializedName("13010")
        public Integer c13010;
        @SerializedName("13011")
        public Integer c13011;
        @SerializedName("13012")
        public Integer c13012;
        @SerializedName("13013")
        public Integer c13013;
        @SerializedName("13014")
        public Integer c13014;
        @SerializedName("13015")
        public Integer c13015;
        @SerializedName("13016")
        public Integer c13016;
        @SerializedName("13017")
        public Integer c13017;
        @SerializedName("13018")
        public Integer c13018;
        @SerializedName("13019")
        public Integer c13019;
        @SerializedName("13020")
        public Integer c13020;
        @SerializedName("13021")
        public Integer c13021;
        @SerializedName("13022")
        public Integer c13022;
        @SerializedName("13023")
        public Integer c13023;
        @SerializedName("13025")
        public Integer c13025;
        @SerializedName("13026")
        public Integer c13026;
        @SerializedName("13027")
        public Integer c13027;
        @SerializedName("13028")
        public Integer c13028;
        @SerializedName("13029")
        public Integer c13029;
        @SerializedName("13030")
        public Integer c13030;
        @SerializedName("13031")
        public Integer c13031;
        @SerializedName("13035")
        public Integer c13035;
        @SerializedName("13038")
        public Integer c13038;
        @SerializedName("13039")
        public Integer c13039;
        @SerializedName("13040")
        public Integer c13040;
        @SerializedName("13041")
        public Integer c13041;
        @SerializedName("13043")
        public Integer c13043;
        @SerializedName("13045")
        public Integer c13045;
        @SerializedName("14001")
        public Integer c14001;
        @SerializedName("14002")
        public Integer c14002;
        @SerializedName("14003")
        public Integer c14003;
        @SerializedName("14004")
        public Integer c14004;
        @SerializedName("14005")
        public Integer c14005;
        @SerializedName("14006")
        public Integer c14006;
        @SerializedName("14007")
        public Integer c14007;
        @SerializedName("14008")
        public Integer c14008;
        @SerializedName("14009")
        public Integer c14009;
        @SerializedName("14010")
        public Integer c14010;
        @SerializedName("14011")
        public Integer c14011;
        @SerializedName("14012")
        public Integer c14012;
        @SerializedName("14013")
        public Integer c14013;
        @SerializedName("14014")
        public Integer c14014;
        @SerializedName("14015")
        public Integer c14015;
        @SerializedName("14016")
        public Integer c14016;
        @SerializedName("14017")
        public Integer c14017;
        @SerializedName("14018")
        public Integer c14018;
        @SerializedName("14019")
        public Integer c14019;
        @SerializedName("14020")
        public Integer c14020;
        @SerializedName("14021")
        public Integer c14021;
        @SerializedName("14022")
        public Integer c14022;
        @SerializedName("14023")
        public Integer c14023;
        @SerializedName("14025")
        public Integer c14025;
        @SerializedName("14026")
        public Integer c14026;
        @SerializedName("14027")
        public Integer c14027;
        @SerializedName("14028")
        public Integer c14028;
        @SerializedName("14029")
        public Integer c14029;
        @SerializedName("14030")
        public Integer c14030;
        @SerializedName("14031")
        public Integer c14031;
        @SerializedName("14035")
        public Integer c14035;
        @SerializedName("14038")
        public Integer c14038;
        @SerializedName("14039")
        public Integer c14039;
        @SerializedName("14040")
        public Integer c14040;
        @SerializedName("14041")
        public Integer c14041;
        @SerializedName("14043")
        public Integer c14043;
        @SerializedName("14045")
        public Integer c14045;
        @SerializedName("15001")
        public Integer c15001;
        @SerializedName("15002")
        public Integer c15002;
        @SerializedName("15003")
        public Integer c15003;
        @SerializedName("15004")
        public Integer c15004;
        @SerializedName("15005")
        public Integer c15005;
        @SerializedName("15006")
        public Integer c15006;
        @SerializedName("15007")
        public Integer c15007;
        @SerializedName("15008")
        public Integer c15008;
        @SerializedName("15009")
        public Integer c15009;
        @SerializedName("15010")
        public Integer c15010;
        @SerializedName("15011")
        public Integer c15011;
        @SerializedName("15012")
        public Integer c15012;
        @SerializedName("15013")
        public Integer c15013;
        @SerializedName("15014")
        public Integer c15014;
        @SerializedName("15015")
        public Integer c15015;
        @SerializedName("15016")
        public Integer c15016;
        @SerializedName("15017")
        public Integer c15017;
        @SerializedName("15018")
        public Integer c15018;
        @SerializedName("15019")
        public Integer c15019;
        @SerializedName("15020")
        public Integer c15020;
        @SerializedName("15021")
        public Integer c15021;
        @SerializedName("15022")
        public Integer c15022;
        @SerializedName("15023")
        public Integer c15023;
        @SerializedName("15025")
        public Integer c15025;
        @SerializedName("15026")
        public Integer c15026;
        @SerializedName("15027")
        public Integer c15027;
        @SerializedName("15028")
        public Integer c15028;
        @SerializedName("15029")
        public Integer c15029;
        @SerializedName("15030")
        public Integer c15030;
        @SerializedName("15031")
        public Integer c15031;
        @SerializedName("15035")
        public Integer c15035;
        @SerializedName("15038")
        public Integer c15038;
        @SerializedName("15039")
        public Integer c15039;
        @SerializedName("15040")
        public Integer c15040;
        @SerializedName("15041")
        public Integer c15041;
        @SerializedName("15043")
        public Integer c15043;
        @SerializedName("15045")
        public Integer c15045;
        @SerializedName("40001")
        public Integer c40001;
        @SerializedName("40002")
        public Integer c40002;
        @SerializedName("40003")
        public Integer c40003;
        @SerializedName("40004")
        public Integer c40004;
        @SerializedName("40005")
        public Integer c40005;
        @SerializedName("40006")
        public Integer c40006;
        @SerializedName("40007")
        public Integer c40007;
        @SerializedName("40008")
        public Integer c40008;
        @SerializedName("40009")
        public Integer c40009;
        @SerializedName("40010")
        public Integer c40010;
        @SerializedName("40011")
        public Integer c40011;
        @SerializedName("40012")
        public Integer c40012;
        @SerializedName("40013")
        public Integer c40013;
        @SerializedName("40014")
        public Integer c40014;
        @SerializedName("40015")
        public Integer c40015;
        @SerializedName("40016")
        public Integer c40016;
        @SerializedName("40017")
        public Integer c40017;
        @SerializedName("40018")
        public Integer c40018;
        @SerializedName("40019")
        public Integer c40019;
        @SerializedName("40020")
        public Integer c40020;
        @SerializedName("40021")
        public Integer c40021;
        @SerializedName("40022")
        public Integer c40022;
        @SerializedName("40023")
        public Integer c40023;
        @SerializedName("40025")
        public Integer c40025;
        @SerializedName("40026")
        public Integer c40026;
        @SerializedName("40027")
        public Integer c40027;
        @SerializedName("40028")
        public Integer c40028;
        @SerializedName("40029")
        public Integer c40029;
        @SerializedName("40030")
        public Integer c40030;
        @SerializedName("40031")
        public Integer c40031;
        @SerializedName("40035")
        public Integer c40035;
        @SerializedName("40038")
        public Integer c40038;
        @SerializedName("40039")
        public Integer c40039;
        @SerializedName("40040")
        public Integer c40040;
        @SerializedName("40041")
        public Integer c40041;
        @SerializedName("40043")
        public Integer c40043;
        @SerializedName("40045")
        public Integer c40045;
        @SerializedName("8")
        public Integer c8;
        @SerializedName("16001")
        public Integer c16001;
        @SerializedName("16002")
        public Integer c16002;
        @SerializedName("16003")
        public Integer c16003;
        @SerializedName("16004")
        public Integer c16004;
        @SerializedName("16005")
        public Integer c16005;
        @SerializedName("16006")
        public Integer c16006;
        @SerializedName("16007")
        public Integer c16007;
        @SerializedName("16008")
        public Integer c16008;
        @SerializedName("16009")
        public Integer c16009;
        @SerializedName("16010")
        public Integer c16010;
        @SerializedName("16011")
        public Integer c16011;
        @SerializedName("16012")
        public Integer c16012;
        @SerializedName("16013")
        public Integer c16013;
        @SerializedName("16014")
        public Integer c16014;
        @SerializedName("16015")
        public Integer c16015;
        @SerializedName("16016")
        public Integer c16016;
        @SerializedName("16017")
        public Integer c16017;
        @SerializedName("16018")
        public Integer c16018;
        @SerializedName("16019")
        public Integer c16019;
        @SerializedName("16020")
        public Integer c16020;
        @SerializedName("16021")
        public Integer c16021;
        @SerializedName("16022")
        public Integer c16022;
        @SerializedName("16023")
        public Integer c16023;
        @SerializedName("16025")
        public Integer c16025;
        @SerializedName("16026")
        public Integer c16026;
        @SerializedName("16027")
        public Integer c16027;
        @SerializedName("16028")
        public Integer c16028;
        @SerializedName("16029")
        public Integer c16029;
        @SerializedName("16030")
        public Integer c16030;
        @SerializedName("16031")
        public Integer c16031;
        @SerializedName("16035")
        public Integer c16035;
        @SerializedName("16038")
        public Integer c16038;
        @SerializedName("16039")
        public Integer c16039;
        @SerializedName("16040")
        public Integer c16040;
        @SerializedName("16041")
        public Integer c16041;
        @SerializedName("16043")
        public Integer c16043;
        @SerializedName("16045")
        public Integer c16045;
        @SerializedName("70")
        public Integer c70;
        @SerializedName("71")
        public Integer c71;
        @SerializedName("17001")
        public Integer c17001;
        @SerializedName("17002")
        public Integer c17002;
        @SerializedName("17003")
        public Integer c17003;
        @SerializedName("17004")
        public Integer c17004;
        @SerializedName("17005")
        public Integer c17005;
        @SerializedName("17006")
        public Integer c17006;
        @SerializedName("17007")
        public Integer c17007;
        @SerializedName("17008")
        public Integer c17008;
        @SerializedName("17009")
        public Integer c17009;
        @SerializedName("17010")
        public Integer c17010;
        @SerializedName("17011")
        public Integer c17011;
        @SerializedName("17012")
        public Integer c17012;
        @SerializedName("17013")
        public Integer c17013;
        @SerializedName("17014")
        public Integer c17014;
        @SerializedName("17015")
        public Integer c17015;
        @SerializedName("17016")
        public Integer c17016;
        @SerializedName("17017")
        public Integer c17017;
        @SerializedName("17018")
        public Integer c17018;
        @SerializedName("17019")
        public Integer c17019;
        @SerializedName("17020")
        public Integer c17020;
        @SerializedName("17021")
        public Integer c17021;
        @SerializedName("17022")
        public Integer c17022;
        @SerializedName("17023")
        public Integer c17023;
        @SerializedName("17025")
        public Integer c17025;
        @SerializedName("17026")
        public Integer c17026;
        @SerializedName("17027")
        public Integer c17027;
        @SerializedName("17028")
        public Integer c17028;
        @SerializedName("17029")
        public Integer c17029;
        @SerializedName("17030")
        public Integer c17030;
        @SerializedName("17031")
        public Integer c17031;
        @SerializedName("17035")
        public Integer c17035;
        @SerializedName("17038")
        public Integer c17038;
        @SerializedName("17039")
        public Integer c17039;
        @SerializedName("17040")
        public Integer c17040;
        @SerializedName("17041")
        public Integer c17041;
        @SerializedName("17043")
        public Integer c17043;
        @SerializedName("17045")
        public Integer c17045;
        @SerializedName("18001")
        public Integer c18001;
        @SerializedName("18002")
        public Integer c18002;
        @SerializedName("18003")
        public Integer c18003;
        @SerializedName("18004")
        public Integer c18004;
        @SerializedName("18005")
        public Integer c18005;
        @SerializedName("18006")
        public Integer c18006;
        @SerializedName("18007")
        public Integer c18007;
        @SerializedName("18008")
        public Integer c18008;
        @SerializedName("18009")
        public Integer c18009;
        @SerializedName("18010")
        public Integer c18010;
        @SerializedName("18011")
        public Integer c18011;
        @SerializedName("18012")
        public Integer c18012;
        @SerializedName("18013")
        public Integer c18013;
        @SerializedName("18014")
        public Integer c18014;
        @SerializedName("18015")
        public Integer c18015;
        @SerializedName("18016")
        public Integer c18016;
        @SerializedName("18017")
        public Integer c18017;
        @SerializedName("18018")
        public Integer c18018;
        @SerializedName("18019")
        public Integer c18019;
        @SerializedName("18020")
        public Integer c18020;
        @SerializedName("18021")
        public Integer c18021;
        @SerializedName("18022")
        public Integer c18022;
        @SerializedName("18023")
        public Integer c18023;
        @SerializedName("18025")
        public Integer c18025;
        @SerializedName("18026")
        public Integer c18026;
        @SerializedName("18027")
        public Integer c18027;
        @SerializedName("18028")
        public Integer c18028;
        @SerializedName("18029")
        public Integer c18029;
        @SerializedName("18030")
        public Integer c18030;
        @SerializedName("18031")
        public Integer c18031;
        @SerializedName("18035")
        public Integer c18035;
        @SerializedName("18038")
        public Integer c18038;
        @SerializedName("18039")
        public Integer c18039;
        @SerializedName("18040")
        public Integer c18040;
        @SerializedName("18041")
        public Integer c18041;
        @SerializedName("18043")
        public Integer c18043;
        @SerializedName("18045")
        public Integer c18045;
        @SerializedName("19001")
        public Integer c19001;
        @SerializedName("19002")
        public Integer c19002;
        @SerializedName("19003")
        public Integer c19003;
        @SerializedName("19004")
        public Integer c19004;
        @SerializedName("19005")
        public Integer c19005;
        @SerializedName("19006")
        public Integer c19006;
        @SerializedName("19007")
        public Integer c19007;
        @SerializedName("19008")
        public Integer c19008;
        @SerializedName("19009")
        public Integer c19009;
        @SerializedName("19010")
        public Integer c19010;
        @SerializedName("19011")
        public Integer c19011;
        @SerializedName("19012")
        public Integer c19012;
        @SerializedName("19013")
        public Integer c19013;
        @SerializedName("19014")
        public Integer c19014;
        @SerializedName("19015")
        public Integer c19015;
        @SerializedName("19016")
        public Integer c19016;
        @SerializedName("19017")
        public Integer c19017;
        @SerializedName("19018")
        public Integer c19018;
        @SerializedName("19019")
        public Integer c19019;
        @SerializedName("19020")
        public Integer c19020;
        @SerializedName("19021")
        public Integer c19021;
        @SerializedName("19022")
        public Integer c19022;
        @SerializedName("19023")
        public Integer c19023;
        @SerializedName("19025")
        public Integer c19025;
        @SerializedName("19026")
        public Integer c19026;
        @SerializedName("19027")
        public Integer c19027;
        @SerializedName("19028")
        public Integer c19028;
        @SerializedName("19029")
        public Integer c19029;
        @SerializedName("19030")
        public Integer c19030;
        @SerializedName("19031")
        public Integer c19031;
        @SerializedName("19035")
        public Integer c19035;
        @SerializedName("19038")
        public Integer c19038;
        @SerializedName("19039")
        public Integer c19039;
        @SerializedName("19040")
        public Integer c19040;
        @SerializedName("19041")
        public Integer c19041;
        @SerializedName("19043")
        public Integer c19043;
        @SerializedName("19045")
        public Integer c19045;
        @SerializedName("20001")
        public Integer c20001;
        @SerializedName("20002")
        public Integer c20002;
        @SerializedName("20003")
        public Integer c20003;
        @SerializedName("20004")
        public Integer c20004;
        @SerializedName("20005")
        public Integer c20005;
        @SerializedName("20006")
        public Integer c20006;
        @SerializedName("20007")
        public Integer c20007;
        @SerializedName("20008")
        public Integer c20008;
        @SerializedName("20009")
        public Integer c20009;
        @SerializedName("20010")
        public Integer c20010;
        @SerializedName("20011")
        public Integer c20011;
        @SerializedName("20012")
        public Integer c20012;
        @SerializedName("20013")
        public Integer c20013;
        @SerializedName("20014")
        public Integer c20014;
        @SerializedName("20015")
        public Integer c20015;
        @SerializedName("20016")
        public Integer c20016;
        @SerializedName("20017")
        public Integer c20017;
        @SerializedName("20018")
        public Integer c20018;
        @SerializedName("20019")
        public Integer c20019;
        @SerializedName("20020")
        public Integer c20020;
        @SerializedName("20021")
        public Integer c20021;
        @SerializedName("20022")
        public Integer c20022;
        @SerializedName("20023")
        public Integer c20023;
        @SerializedName("20025")
        public Integer c20025;
        @SerializedName("20026")
        public Integer c20026;
        @SerializedName("20027")
        public Integer c20027;
        @SerializedName("20028")
        public Integer c20028;
        @SerializedName("20029")
        public Integer c20029;
        @SerializedName("20030")
        public Integer c20030;
        @SerializedName("20031")
        public Integer c20031;
        @SerializedName("20035")
        public Integer c20035;
        @SerializedName("20038")
        public Integer c20038;
        @SerializedName("20039")
        public Integer c20039;
        @SerializedName("20040")
        public Integer c20040;
        @SerializedName("20041")
        public Integer c20041;
        @SerializedName("20043")
        public Integer c20043;
        @SerializedName("20045")
        public Integer c20045;
        @SerializedName("21001")
        public Integer c21001;
        @SerializedName("21002")
        public Integer c21002;
        @SerializedName("21003")
        public Integer c21003;
        @SerializedName("21004")
        public Integer c21004;
        @SerializedName("21005")
        public Integer c21005;
        @SerializedName("21006")
        public Integer c21006;
        @SerializedName("21007")
        public Integer c21007;
        @SerializedName("21008")
        public Integer c21008;
        @SerializedName("21009")
        public Integer c21009;
        @SerializedName("21010")
        public Integer c21010;
        @SerializedName("21011")
        public Integer c21011;
        @SerializedName("21012")
        public Integer c21012;
        @SerializedName("21013")
        public Integer c21013;
        @SerializedName("21014")
        public Integer c21014;
        @SerializedName("21015")
        public Integer c21015;
        @SerializedName("21016")
        public Integer c21016;
        @SerializedName("21017")
        public Integer c21017;
        @SerializedName("21018")
        public Integer c21018;
        @SerializedName("21019")
        public Integer c21019;
        @SerializedName("21020")
        public Integer c21020;
        @SerializedName("21021")
        public Integer c21021;
        @SerializedName("21022")
        public Integer c21022;
        @SerializedName("21023")
        public Integer c21023;
        @SerializedName("21025")
        public Integer c21025;
        @SerializedName("21026")
        public Integer c21026;
        @SerializedName("21027")
        public Integer c21027;
        @SerializedName("21028")
        public Integer c21028;
        @SerializedName("21029")
        public Integer c21029;
        @SerializedName("21030")
        public Integer c21030;
        @SerializedName("21031")
        public Integer c21031;
        @SerializedName("21035")
        public Integer c21035;
        @SerializedName("21038")
        public Integer c21038;
        @SerializedName("21039")
        public Integer c21039;
        @SerializedName("21040")
        public Integer c21040;
        @SerializedName("21041")
        public Integer c21041;
        @SerializedName("21043")
        public Integer c21043;
        @SerializedName("21045")
        public Integer c21045;
        @SerializedName("22001")
        public Integer c22001;
        @SerializedName("22002")
        public Integer c22002;
        @SerializedName("22003")
        public Integer c22003;
        @SerializedName("22004")
        public Integer c22004;
        @SerializedName("22005")
        public Integer c22005;
        @SerializedName("22006")
        public Integer c22006;
        @SerializedName("22007")
        public Integer c22007;
        @SerializedName("22008")
        public Integer c22008;
        @SerializedName("22009")
        public Integer c22009;
        @SerializedName("22010")
        public Integer c22010;
        @SerializedName("22011")
        public Integer c22011;
        @SerializedName("22012")
        public Integer c22012;
        @SerializedName("22013")
        public Integer c22013;
        @SerializedName("22014")
        public Integer c22014;
        @SerializedName("22015")
        public Integer c22015;
        @SerializedName("22016")
        public Integer c22016;
        @SerializedName("22017")
        public Integer c22017;
        @SerializedName("22018")
        public Integer c22018;
        @SerializedName("22019")
        public Integer c22019;
        @SerializedName("22020")
        public Integer c22020;
        @SerializedName("22021")
        public Integer c22021;
        @SerializedName("22022")
        public Integer c22022;
        @SerializedName("22023")
        public Integer c22023;
        @SerializedName("22025")
        public Integer c22025;
        @SerializedName("22026")
        public Integer c22026;
        @SerializedName("22027")
        public Integer c22027;
        @SerializedName("22028")
        public Integer c22028;
        @SerializedName("22029")
        public Integer c22029;
        @SerializedName("22030")
        public Integer c22030;
        @SerializedName("22031")
        public Integer c22031;
        @SerializedName("22035")
        public Integer c22035;
        @SerializedName("22038")
        public Integer c22038;
        @SerializedName("22039")
        public Integer c22039;
        @SerializedName("22040")
        public Integer c22040;
        @SerializedName("22041")
        public Integer c22041;
        @SerializedName("22043")
        public Integer c22043;
        @SerializedName("22045")
        public Integer c22045;
        @SerializedName("24001")
        public Integer c24001;
        @SerializedName("24002")
        public Integer c24002;
        @SerializedName("24003")
        public Integer c24003;
        @SerializedName("24004")
        public Integer c24004;
        @SerializedName("24005")
        public Integer c24005;
        @SerializedName("24006")
        public Integer c24006;
        @SerializedName("24007")
        public Integer c24007;
        @SerializedName("24008")
        public Integer c24008;
        @SerializedName("24009")
        public Integer c24009;
        @SerializedName("24010")
        public Integer c24010;
        @SerializedName("24011")
        public Integer c24011;
        @SerializedName("24012")
        public Integer c24012;
        @SerializedName("24013")
        public Integer c24013;
        @SerializedName("24014")
        public Integer c24014;
        @SerializedName("24015")
        public Integer c24015;
        @SerializedName("24016")
        public Integer c24016;
        @SerializedName("24017")
        public Integer c24017;
        @SerializedName("24018")
        public Integer c24018;
        @SerializedName("24019")
        public Integer c24019;
        @SerializedName("24020")
        public Integer c24020;
        @SerializedName("24021")
        public Integer c24021;
        @SerializedName("24022")
        public Integer c24022;
        @SerializedName("24023")
        public Integer c24023;
        @SerializedName("24025")
        public Integer c24025;
        @SerializedName("24026")
        public Integer c24026;
        @SerializedName("24027")
        public Integer c24027;
        @SerializedName("24028")
        public Integer c24028;
        @SerializedName("24029")
        public Integer c24029;
        @SerializedName("24030")
        public Integer c24030;
        @SerializedName("24031")
        public Integer c24031;
        @SerializedName("24035")
        public Integer c24035;
        @SerializedName("24038")
        public Integer c24038;
        @SerializedName("24039")
        public Integer c24039;
        @SerializedName("24040")
        public Integer c24040;
        @SerializedName("24041")
        public Integer c24041;
        @SerializedName("24043")
        public Integer c24043;
        @SerializedName("24045")
        public Integer c24045;
        @SerializedName("23001")
        public Integer c23001;
        @SerializedName("23002")
        public Integer c23002;
        @SerializedName("23003")
        public Integer c23003;
        @SerializedName("23004")
        public Integer c23004;
        @SerializedName("23005")
        public Integer c23005;
        @SerializedName("23006")
        public Integer c23006;
        @SerializedName("23007")
        public Integer c23007;
        @SerializedName("23008")
        public Integer c23008;
        @SerializedName("23009")
        public Integer c23009;
        @SerializedName("23010")
        public Integer c23010;
        @SerializedName("23011")
        public Integer c23011;
        @SerializedName("23012")
        public Integer c23012;
        @SerializedName("23013")
        public Integer c23013;
        @SerializedName("23014")
        public Integer c23014;
        @SerializedName("23015")
        public Integer c23015;
        @SerializedName("23016")
        public Integer c23016;
        @SerializedName("23017")
        public Integer c23017;
        @SerializedName("23018")
        public Integer c23018;
        @SerializedName("23019")
        public Integer c23019;
        @SerializedName("23020")
        public Integer c23020;
        @SerializedName("23021")
        public Integer c23021;
        @SerializedName("23022")
        public Integer c23022;
        @SerializedName("23023")
        public Integer c23023;
        @SerializedName("23025")
        public Integer c23025;
        @SerializedName("23026")
        public Integer c23026;
        @SerializedName("23027")
        public Integer c23027;
        @SerializedName("23028")
        public Integer c23028;
        @SerializedName("23029")
        public Integer c23029;
        @SerializedName("23030")
        public Integer c23030;
        @SerializedName("23031")
        public Integer c23031;
        @SerializedName("23035")
        public Integer c23035;
        @SerializedName("23038")
        public Integer c23038;
        @SerializedName("23039")
        public Integer c23039;
        @SerializedName("23040")
        public Integer c23040;
        @SerializedName("23041")
        public Integer c23041;
        @SerializedName("23043")
        public Integer c23043;
        @SerializedName("23045")
        public Integer c23045;
        @SerializedName("12")
        public Integer c12;
        @SerializedName("13")
        public Integer c13;
        @SerializedName("17")
        public Integer c17;
        @SerializedName("16")
        public Integer c16;
        @SerializedName("15")
        public Integer c15;
        @SerializedName("11")
        public Integer c11;
        @SerializedName("10")
        public Integer c10;
        @SerializedName("14")
        public Integer c14;
        @SerializedName("26001")
        public Integer c26001;
        @SerializedName("26002")
        public Integer c26002;
        @SerializedName("26003")
        public Integer c26003;
        @SerializedName("26004")
        public Integer c26004;
        @SerializedName("26005")
        public Integer c26005;
        @SerializedName("26006")
        public Integer c26006;
        @SerializedName("26007")
        public Integer c26007;
        @SerializedName("26008")
        public Integer c26008;
        @SerializedName("26009")
        public Integer c26009;
        @SerializedName("26010")
        public Integer c26010;
        @SerializedName("26011")
        public Integer c26011;
        @SerializedName("26012")
        public Integer c26012;
        @SerializedName("26013")
        public Integer c26013;
        @SerializedName("26014")
        public Integer c26014;
        @SerializedName("26015")
        public Integer c26015;
        @SerializedName("26016")
        public Integer c26016;
        @SerializedName("26017")
        public Integer c26017;
        @SerializedName("26018")
        public Integer c26018;
        @SerializedName("26019")
        public Integer c26019;
        @SerializedName("26020")
        public Integer c26020;
        @SerializedName("26021")
        public Integer c26021;
        @SerializedName("26022")
        public Integer c26022;
        @SerializedName("26023")
        public Integer c26023;
        @SerializedName("26025")
        public Integer c26025;
        @SerializedName("26026")
        public Integer c26026;
        @SerializedName("26027")
        public Integer c26027;
        @SerializedName("26028")
        public Integer c26028;
        @SerializedName("26029")
        public Integer c26029;
        @SerializedName("26030")
        public Integer c26030;
        @SerializedName("26031")
        public Integer c26031;
        @SerializedName("26035")
        public Integer c26035;
        @SerializedName("26038")
        public Integer c26038;
        @SerializedName("26039")
        public Integer c26039;
        @SerializedName("26040")
        public Integer c26040;
        @SerializedName("26041")
        public Integer c26041;
        @SerializedName("26043")
        public Integer c26043;
        @SerializedName("26045")
        public Integer c26045;
        @SerializedName("25001")
        public Integer c25001;
        @SerializedName("25002")
        public Integer c25002;
        @SerializedName("25003")
        public Integer c25003;
        @SerializedName("25004")
        public Integer c25004;
        @SerializedName("25005")
        public Integer c25005;
        @SerializedName("25006")
        public Integer c25006;
        @SerializedName("25007")
        public Integer c25007;
        @SerializedName("25008")
        public Integer c25008;
        @SerializedName("25009")
        public Integer c25009;
        @SerializedName("25010")
        public Integer c25010;
        @SerializedName("25011")
        public Integer c25011;
        @SerializedName("25012")
        public Integer c25012;
        @SerializedName("25013")
        public Integer c25013;
        @SerializedName("25014")
        public Integer c25014;
        @SerializedName("25015")
        public Integer c25015;
        @SerializedName("25016")
        public Integer c25016;
        @SerializedName("25017")
        public Integer c25017;
        @SerializedName("25018")
        public Integer c25018;
        @SerializedName("25019")
        public Integer c25019;
        @SerializedName("25020")
        public Integer c25020;
        @SerializedName("25021")
        public Integer c25021;
        @SerializedName("25022")
        public Integer c25022;
        @SerializedName("25023")
        public Integer c25023;
        @SerializedName("25025")
        public Integer c25025;
        @SerializedName("25026")
        public Integer c25026;
        @SerializedName("25027")
        public Integer c25027;
        @SerializedName("25028")
        public Integer c25028;
        @SerializedName("25029")
        public Integer c25029;
        @SerializedName("25030")
        public Integer c25030;
        @SerializedName("25031")
        public Integer c25031;
        @SerializedName("25035")
        public Integer c25035;
        @SerializedName("25038")
        public Integer c25038;
        @SerializedName("25039")
        public Integer c25039;
        @SerializedName("25040")
        public Integer c25040;
        @SerializedName("25041")
        public Integer c25041;
        @SerializedName("25043")
        public Integer c25043;
        @SerializedName("25045")
        public Integer c25045;
        @SerializedName("18")
        public Integer c18;
        @SerializedName("19")
        public Integer c19;
        @SerializedName("25")
        public Integer c25;
        @SerializedName("26")
        public Integer c26;
        @SerializedName("27001")
        public Integer c27001;
        @SerializedName("27002")
        public Integer c27002;
        @SerializedName("27003")
        public Integer c27003;
        @SerializedName("27004")
        public Integer c27004;
        @SerializedName("27005")
        public Integer c27005;
        @SerializedName("27006")
        public Integer c27006;
        @SerializedName("27007")
        public Integer c27007;
        @SerializedName("27008")
        public Integer c27008;
        @SerializedName("27009")
        public Integer c27009;
        @SerializedName("27010")
        public Integer c27010;
        @SerializedName("27011")
        public Integer c27011;
        @SerializedName("27012")
        public Integer c27012;
        @SerializedName("27013")
        public Integer c27013;
        @SerializedName("27014")
        public Integer c27014;
        @SerializedName("27015")
        public Integer c27015;
        @SerializedName("27016")
        public Integer c27016;
        @SerializedName("27017")
        public Integer c27017;
        @SerializedName("27018")
        public Integer c27018;
        @SerializedName("27019")
        public Integer c27019;
        @SerializedName("27020")
        public Integer c27020;
        @SerializedName("27021")
        public Integer c27021;
        @SerializedName("27022")
        public Integer c27022;
        @SerializedName("27023")
        public Integer c27023;
        @SerializedName("27025")
        public Integer c27025;
        @SerializedName("27026")
        public Integer c27026;
        @SerializedName("27027")
        public Integer c27027;
        @SerializedName("27028")
        public Integer c27028;
        @SerializedName("27029")
        public Integer c27029;
        @SerializedName("27030")
        public Integer c27030;
        @SerializedName("27031")
        public Integer c27031;
        @SerializedName("27035")
        public Integer c27035;
        @SerializedName("27038")
        public Integer c27038;
        @SerializedName("27039")
        public Integer c27039;
        @SerializedName("27040")
        public Integer c27040;
        @SerializedName("27041")
        public Integer c27041;
        @SerializedName("27043")
        public Integer c27043;
        @SerializedName("27045")
        public Integer c27045;
        @SerializedName("28001")
        public Integer c28001;
        @SerializedName("28002")
        public Integer c28002;
        @SerializedName("28003")
        public Integer c28003;
        @SerializedName("28004")
        public Integer c28004;
        @SerializedName("28005")
        public Integer c28005;
        @SerializedName("28006")
        public Integer c28006;
        @SerializedName("28007")
        public Integer c28007;
        @SerializedName("28008")
        public Integer c28008;
        @SerializedName("28009")
        public Integer c28009;
        @SerializedName("28010")
        public Integer c28010;
        @SerializedName("28011")
        public Integer c28011;
        @SerializedName("28012")
        public Integer c28012;
        @SerializedName("28013")
        public Integer c28013;
        @SerializedName("28014")
        public Integer c28014;
        @SerializedName("28015")
        public Integer c28015;
        @SerializedName("28016")
        public Integer c28016;
        @SerializedName("28017")
        public Integer c28017;
        @SerializedName("28018")
        public Integer c28018;
        @SerializedName("28019")
        public Integer c28019;
        @SerializedName("28020")
        public Integer c28020;
        @SerializedName("28021")
        public Integer c28021;
        @SerializedName("28022")
        public Integer c28022;
        @SerializedName("28023")
        public Integer c28023;
        @SerializedName("28025")
        public Integer c28025;
        @SerializedName("28026")
        public Integer c28026;
        @SerializedName("28027")
        public Integer c28027;
        @SerializedName("28028")
        public Integer c28028;
        @SerializedName("28029")
        public Integer c28029;
        @SerializedName("28030")
        public Integer c28030;
        @SerializedName("28031")
        public Integer c28031;
        @SerializedName("28035")
        public Integer c28035;
        @SerializedName("28038")
        public Integer c28038;
        @SerializedName("28039")
        public Integer c28039;
        @SerializedName("28040")
        public Integer c28040;
        @SerializedName("28041")
        public Integer c28041;
        @SerializedName("28043")
        public Integer c28043;
        @SerializedName("28045")
        public Integer c28045;
        @SerializedName("22")
        public Integer c22;
        @SerializedName("23")
        public Integer c23;
        @SerializedName("27")
        public Integer c27;
        @SerializedName("200003")
        public Integer c200003;
        @SerializedName("200004")
        public Integer c200004;
        @SerializedName("200005")
        public Integer c200005;
        @SerializedName("200006")
        public Integer c200006;
        @SerializedName("200007")
        public Integer c200007;
        @SerializedName("200008")
        public Integer c200008;
        @SerializedName("200009")
        public Integer c200009;
        @SerializedName("200010")
        public Integer c200010;
        @SerializedName("200011")
        public Integer c200011;
        @SerializedName("200012")
        public Integer c200012;
        @SerializedName("200013")
        public Integer c200013;
        @SerializedName("200014")
        public Integer c200014;
        @SerializedName("200015")
        public Integer c200015;
        @SerializedName("200016")
        public Integer c200016;
        @SerializedName("200017")
        public Integer c200017;
        @SerializedName("200018")
        public Integer c200018;
        @SerializedName("200019")
        public Integer c200019;
        @SerializedName("200020")
        public Integer c200020;
        @SerializedName("200021")
        public Integer c200021;
        @SerializedName("200022")
        public Integer c200022;
        @SerializedName("200023")
        public Integer c200023;
        @SerializedName("200024")
        public Integer c200024;
        @SerializedName("200025")
        public Integer c200025;
        @SerializedName("200026")
        public Integer c200026;
        @SerializedName("200027")
        public Integer c200027;
        @SerializedName("200028")
        public Integer c200028;
        @SerializedName("200029")
        public Integer c200029;
        @SerializedName("200030")
        public Integer c200030;
        @SerializedName("200031")
        public Integer c200031;
        @SerializedName("200032")
        public Integer c200032;
        @SerializedName("200033")
        public Integer c200033;
        @SerializedName("200034")
        public Integer c200034;
        @SerializedName("200035")
        public Integer c200035;
        @SerializedName("200036")
        public Integer c200036;
        @SerializedName("200037")
        public Integer c200037;
        @SerializedName("200038")
        public Integer c200038;
        @SerializedName("200039")
        public Integer c200039;
        @SerializedName("200040")
        public Integer c200040;
        @SerializedName("200041")
        public Integer c200041;
        @SerializedName("200042")
        public Integer c200042;
        @SerializedName("200043")
        public Integer c200043;
        @SerializedName("200044")
        public Integer c200044;
        @SerializedName("200045")
        public Integer c200045;
        @SerializedName("200048")
        public Integer c200048;
        @SerializedName("200049")
        public Integer c200049;
        @SerializedName("200050")
        public Integer c200050;
        @SerializedName("200051")
        public Integer c200051;
        @SerializedName("200056")
        public Integer c200056;
        @SerializedName("200057")
        public Integer c200057;
        @SerializedName("200058")
        public Integer c200058;
        @SerializedName("200059")
        public Integer c200059;
        @SerializedName("200062")
        public Integer c200062;
        @SerializedName("200063")
        public Integer c200063;
        @SerializedName("200064")
        public Integer c200064;
        @SerializedName("200065")
        public Integer c200065;
        @SerializedName("200066")
        public Integer c200066;
        @SerializedName("200067")
        public Integer c200067;
        @SerializedName("200069")
        public Integer c200069;
        @SerializedName("200070")
        public Integer c200070;
        @SerializedName("200071")
        public Integer c200071;
        @SerializedName("200072")
        public Integer c200072;
        @SerializedName("200073")
        public Integer c200073;
        @SerializedName("200074")
        public Integer c200074;
        @SerializedName("200076")
        public Integer c200076;
        @SerializedName("200077")
        public Integer c200077;
        @SerializedName("200078")
        public Integer c200078;
        @SerializedName("200079")
        public Integer c200079;
        @SerializedName("200080")
        public Integer c200080;
        @SerializedName("200081")
        public Integer c200081;
        @SerializedName("200082")
        public Integer c200082;
        @SerializedName("200083")
        public Integer c200083;
        @SerializedName("200084")
        public Integer c200084;
        @SerializedName("200085")
        public Integer c200085;
        @SerializedName("200086")
        public Integer c200086;
        @SerializedName("200087")
        public Integer c200087;
        @SerializedName("200088")
        public Integer c200088;
        @SerializedName("200089")
        public Integer c200089;
        @SerializedName("200090")
        public Integer c200090;
        @SerializedName("200091")
        public Integer c200091;
        @SerializedName("200092")
        public Integer c200092;
        @SerializedName("200093")
        public Integer c200093;
        @SerializedName("200094")
        public Integer c200094;
        @SerializedName("200095")
        public Integer c200095;
        @SerializedName("200096")
        public Integer c200096;
        @SerializedName("200097")
        public Integer c200097;
        @SerializedName("200098")
        public Integer c200098;
        @SerializedName("200099")
        public Integer c200099;
        @SerializedName("200100")
        public Integer c200100;
        @SerializedName("200101")
        public Integer c200101;
        @SerializedName("200102")
        public Integer c200102;
        @SerializedName("200103")
        public Integer c200103;
        @SerializedName("200104")
        public Integer c200104;
        @SerializedName("200105")
        public Integer c200105;
        @SerializedName("200106")
        public Integer c200106;
        @SerializedName("200107")
        public Integer c200107;
        @SerializedName("200108")
        public Integer c200108;
        @SerializedName("200109")
        public Integer c200109;
        @SerializedName("200110")
        public Integer c200110;
        @SerializedName("200111")
        public Integer c200111;
        @SerializedName("200112")
        public Integer c200112;
        @SerializedName("200113")
        public Integer c200113;
        @SerializedName("200114")
        public Integer c200114;
        @SerializedName("200115")
        public Integer c200115;
        @SerializedName("200116")
        public Integer c200116;
        @SerializedName("200117")
        public Integer c200117;
        @SerializedName("200118")
        public Integer c200118;
        @SerializedName("200119")
        public Integer c200119;
        @SerializedName("200120")
        public Integer c200120;
        @SerializedName("200121")
        public Integer c200121;
        @SerializedName("200122")
        public Integer c200122;
        @SerializedName("200124")
        public Integer c200124;
        @SerializedName("200125")
        public Integer c200125;
        @SerializedName("200126")
        public Integer c200126;
        @SerializedName("200128")
        public Integer c200128;
        @SerializedName("200129")
        public Integer c200129;
        @SerializedName("200130")
        public Integer c200130;
        @SerializedName("200131")
        public Integer c200131;
        @SerializedName("200132")
        public Integer c200132;
        @SerializedName("200133")
        public Integer c200133;
        @SerializedName("200134")
        public Integer c200134;
        @SerializedName("200135")
        public Integer c200135;
        @SerializedName("200136")
        public Integer c200136;
        @SerializedName("200137")
        public Integer c200137;
        @SerializedName("200138")
        public Integer c200138;
        @SerializedName("200139")
        public Integer c200139;
        @SerializedName("200140")
        public Integer c200140;
        @SerializedName("200141")
        public Integer c200141;
        @SerializedName("200142")
        public Integer c200142;
        @SerializedName("200143")
        public Integer c200143;
        @SerializedName("200144")
        public Integer c200144;
        @SerializedName("200145")
        public Integer c200145;
        @SerializedName("200146")
        public Integer c200146;
        @SerializedName("200147")
        public Integer c200147;
        @SerializedName("200148")
        public Integer c200148;
        @SerializedName("200149")
        public Integer c200149;
        @SerializedName("200150")
        public Integer c200150;
        @SerializedName("200151")
        public Integer c200151;
        @SerializedName("200152")
        public Integer c200152;
        @SerializedName("200153")
        public Integer c200153;
        @SerializedName("200154")
        public Integer c200154;
        @SerializedName("200155")
        public Integer c200155;
        @SerializedName("200156")
        public Integer c200156;
        @SerializedName("200157")
        public Integer c200157;
        @SerializedName("200158")
        public Integer c200158;
        @SerializedName("200159")
        public Integer c200159;
        @SerializedName("200160")
        public Integer c200160;
        @SerializedName("200161")
        public Integer c200161;
        @SerializedName("200163")
        public Integer c200163;
        @SerializedName("200164")
        public Integer c200164;
        @SerializedName("200165")
        public Integer c200165;
        @SerializedName("200166")
        public Integer c200166;
        @SerializedName("200167")
        public Integer c200167;
        @SerializedName("200168")
        public Integer c200168;
        @SerializedName("200169")
        public Integer c200169;
        @SerializedName("200170")
        public Integer c200170;
        @SerializedName("200171")
        public Integer c200171;
        @SerializedName("200172")
        public Integer c200172;
        @SerializedName("200173")
        public Integer c200173;
        @SerializedName("200174")
        public Integer c200174;
        @SerializedName("200175")
        public Integer c200175;
        @SerializedName("200176")
        public Integer c200176;
        @SerializedName("200177")
        public Integer c200177;
        @SerializedName("200178")
        public Integer c200178;
        @SerializedName("200179")
        public Integer c200179;
        @SerializedName("200180")
        public Integer c200180;
        @SerializedName("200181")
        public Integer c200181;
        @SerializedName("200182")
        public Integer c200182;
        @SerializedName("200183")
        public Integer c200183;
        @SerializedName("200184")
        public Integer c200184;
        @SerializedName("200185")
        public Integer c200185;
        @SerializedName("200186")
        public Integer c200186;
        @SerializedName("200187")
        public Integer c200187;
        @SerializedName("200188")
        public Integer c200188;
        @SerializedName("200189")
        public Integer c200189;
        @SerializedName("200190")
        public Integer c200190;
        @SerializedName("200191")
        public Integer c200191;
        @SerializedName("200192")
        public Integer c200192;
        @SerializedName("200193")
        public Integer c200193;
        @SerializedName("200194")
        public Integer c200194;
        @SerializedName("200195")
        public Integer c200195;
        @SerializedName("200196")
        public Integer c200196;
        @SerializedName("200197")
        public Integer c200197;
        @SerializedName("200198")
        public Integer c200198;
        @SerializedName("200199")
        public Integer c200199;
        @SerializedName("200200")
        public Integer c200200;
        @SerializedName("200201")
        public Integer c200201;
        @SerializedName("200202")
        public Integer c200202;
        @SerializedName("200203")
        public Integer c200203;
        @SerializedName("200204")
        public Integer c200204;
        @SerializedName("200205")
        public Integer c200205;
        @SerializedName("200206")
        public Integer c200206;
        @SerializedName("200207")
        public Integer c200207;
        @SerializedName("200208")
        public Integer c200208;
        @SerializedName("200209")
        public Integer c200209;
        @SerializedName("200210")
        public Integer c200210;
        @SerializedName("200211")
        public Integer c200211;
        @SerializedName("150001")
        public Integer c150001;
        @SerializedName("150002")
        public Integer c150002;
        @SerializedName("150003")
        public Integer c150003;
        @SerializedName("150004")
        public Integer c150004;
        @SerializedName("150005")
        public Integer c150005;
        @SerializedName("150006")
        public Integer c150006;
        @SerializedName("150007")
        public Integer c150007;
        @SerializedName("150008")
        public Integer c150008;
        @SerializedName("150009")
        public Integer c150009;
        @SerializedName("150010")
        public Integer c150010;
        @SerializedName("150011")
        public Integer c150011;
        @SerializedName("150012")
        public Integer c150012;
        @SerializedName("150013")
        public Integer c150013;
        @SerializedName("150014")
        public Integer c150014;
        @SerializedName("150015")
        public Integer c150015;
        @SerializedName("150016")
        public Integer c150016;
        @SerializedName("150017")
        public Integer c150017;
        @SerializedName("150018")
        public Integer c150018;
        @SerializedName("150019")
        public Integer c150019;
        @SerializedName("150020")
        public Integer c150020;
        @SerializedName("150021")
        public Integer c150021;
        @SerializedName("150022")
        public Integer c150022;
        @SerializedName("150023")
        public Integer c150023;
        @SerializedName("150024")
        public Integer c150024;
        @SerializedName("150025")
        public Integer c150025;
        @SerializedName("150026")
        public Integer c150026;
        @SerializedName("150027")
        public Integer c150027;
        @SerializedName("150028")
        public Integer c150028;
        @SerializedName("150029")
        public Integer c150029;
        @SerializedName("150030")
        public Integer c150030;
        @SerializedName("150031")
        public Integer c150031;
        @SerializedName("150032")
        public Integer c150032;
        @SerializedName("150033")
        public Integer c150033;
        @SerializedName("150034")
        public Integer c150034;
        @SerializedName("150035")
        public Integer c150035;
        @SerializedName("150036")
        public Integer c150036;
        @SerializedName("150037")
        public Integer c150037;
        @SerializedName("150038")
        public Integer c150038;
        @SerializedName("150039")
        public Integer c150039;
        @SerializedName("150040")
        public Integer c150040;
        @SerializedName("150041")
        public Integer c150041;
        @SerializedName("150042")
        public Integer c150042;
        @SerializedName("150043")
        public Integer c150043;
        @SerializedName("150044")
        public Integer c150044;
        @SerializedName("150045")
        public Integer c150045;
        @SerializedName("150046")
        public Integer c150046;
        @SerializedName("150047")
        public Integer c150047;
        @SerializedName("150048")
        public Integer c150048;
        @SerializedName("150049")
        public Integer c150049;
        @SerializedName("150050")
        public Integer c150050;
        @SerializedName("150051")
        public Integer c150051;
        @SerializedName("150052")
        public Integer c150052;
        @SerializedName("150053")
        public Integer c150053;
        @SerializedName("150054")
        public Integer c150054;
        @SerializedName("150055")
        public Integer c150055;
        @SerializedName("150056")
        public Integer c150056;
        @SerializedName("150057")
        public Integer c150057;
        @SerializedName("150058")
        public Integer c150058;
        @SerializedName("150059")
        public Integer c150059;
        @SerializedName("150060")
        public Integer c150060;
        @SerializedName("150061")
        public Integer c150061;
        @SerializedName("150062")
        public Integer c150062;
        @SerializedName("150063")
        public Integer c150063;
        @SerializedName("150064")
        public Integer c150064;
        @SerializedName("150065")
        public Integer c150065;
        @SerializedName("150066")
        public Integer c150066;
        @SerializedName("150067")
        public Integer c150067;
        @SerializedName("150068")
        public Integer c150068;
        @SerializedName("150069")
        public Integer c150069;
        @SerializedName("150070")
        public Integer c150070;
        @SerializedName("150071")
        public Integer c150071;
        @SerializedName("150072")
        public Integer c150072;
        @SerializedName("150073")
        public Integer c150073;
        @SerializedName("150074")
        public Integer c150074;
        @SerializedName("150075")
        public Integer c150075;
        @SerializedName("150076")
        public Integer c150076;
        @SerializedName("150078")
        public Integer c150078;
        @SerializedName("150079")
        public Integer c150079;
        @SerializedName("150080")
        public Integer c150080;
        @SerializedName("150081")
        public Integer c150081;
        @SerializedName("150082")
        public Integer c150082;
        @SerializedName("150083")
        public Integer c150083;
        @SerializedName("150084")
        public Integer c150084;
        @SerializedName("150085")
        public Integer c150085;
        @SerializedName("150086")
        public Integer c150086;
        @SerializedName("150087")
        public Integer c150087;
        @SerializedName("150088")
        public Integer c150088;
        @SerializedName("150089")
        public Integer c150089;
        @SerializedName("150090")
        public Integer c150090;
        @SerializedName("150091")
        public Integer c150091;
        @SerializedName("150092")
        public Integer c150092;
        @SerializedName("150093")
        public Integer c150093;
        @SerializedName("150094")
        public Integer c150094;
        @SerializedName("150095")
        public Integer c150095;
        @SerializedName("150096")
        public Integer c150096;
        @SerializedName("150097")
        public Integer c150097;
        @SerializedName("150098")
        public Integer c150098;
        @SerializedName("150099")
        public Integer c150099;
        @SerializedName("150100")
        public Integer c150100;
        @SerializedName("150101")
        public Integer c150101;
        @SerializedName("150102")
        public Integer c150102;
        @SerializedName("150103")
        public Integer c150103;
        @SerializedName("150104")
        public Integer c150104;
        @SerializedName("150105")
        public Integer c150105;
        @SerializedName("150106")
        public Integer c150106;
        @SerializedName("150107")
        public Integer c150107;
        @SerializedName("150108")
        public Integer c150108;
        @SerializedName("150109")
        public Integer c150109;
        @SerializedName("150110")
        public Integer c150110;
        @SerializedName("150111")
        public Integer c150111;
        @SerializedName("150112")
        public Integer c150112;
        @SerializedName("150113")
        public Integer c150113;
        @SerializedName("150114")
        public Integer c150114;
        @SerializedName("150115")
        public Integer c150115;
        @SerializedName("150116")
        public Integer c150116;
        @SerializedName("150117")
        public Integer c150117;
        @SerializedName("150118")
        public Integer c150118;
        @SerializedName("150119")
        public Integer c150119;
        @SerializedName("150120")
        public Integer c150120;
        @SerializedName("150121")
        public Integer c150121;
        @SerializedName("150122")
        public Integer c150122;
        @SerializedName("150123")
        public Integer c150123;
        @SerializedName("150124")
        public Integer c150124;
        @SerializedName("150125")
        public Integer c150125;
        @SerializedName("150126")
        public Integer c150126;
        @SerializedName("150127")
        public Integer c150127;
        @SerializedName("150128")
        public Integer c150128;
        @SerializedName("150129")
        public Integer c150129;
        @SerializedName("150130")
        public Integer c150130;
        @SerializedName("150131")
        public Integer c150131;
        @SerializedName("150132")
        public Integer c150132;
        @SerializedName("150133")
        public Integer c150133;
        @SerializedName("150134")
        public Integer c150134;
        @SerializedName("150135")
        public Integer c150135;
        @SerializedName("150136")
        public Integer c150136;
        @SerializedName("150137")
        public Integer c150137;
        @SerializedName("150138")
        public Integer c150138;
        @SerializedName("150140")
        public Integer c150140;
        @SerializedName("150141")
        public Integer c150141;
        @SerializedName("150142")
        public Integer c150142;
        @SerializedName("150143")
        public Integer c150143;
        @SerializedName("150144")
        public Integer c150144;
        @SerializedName("150149")
        public Integer c150149;
        @SerializedName("150150")
        public Integer c150150;
        @SerializedName("150151")
        public Integer c150151;
        @SerializedName("150152")
        public Integer c150152;
        @SerializedName("150153")
        public Integer c150153;
        @SerializedName("150154")
        public Integer c150154;
        @SerializedName("150155")
        public Integer c150155;
        @SerializedName("150156")
        public Integer c150156;
        @SerializedName("150157")
        public Integer c150157;
        @SerializedName("150158")
        public Integer c150158;
        @SerializedName("150159")
        public Integer c150159;
        @SerializedName("150160")
        public Integer c150160;
        @SerializedName("150161")
        public Integer c150161;
        @SerializedName("150162")
        public Integer c150162;
        @SerializedName("150163")
        public Integer c150163;
        @SerializedName("150164")
        public Integer c150164;
        @SerializedName("150165")
        public Integer c150165;
        @SerializedName("150166")
        public Integer c150166;
        @SerializedName("150167")
        public Integer c150167;
        @SerializedName("150168")
        public Integer c150168;
        @SerializedName("150169")
        public Integer c150169;
        @SerializedName("150170")
        public Integer c150170;
        @SerializedName("150171")
        public Integer c150171;
        @SerializedName("150172")
        public Integer c150172;
        @SerializedName("150173")
        public Integer c150173;
        @SerializedName("150174")
        public Integer c150174;
        @SerializedName("150175")
        public Integer c150175;
        @SerializedName("150176")
        public Integer c150176;
        @SerializedName("150177")
        public Integer c150177;
        @SerializedName("150178")
        public Integer c150178;
        @SerializedName("150179")
        public Integer c150179;
        @SerializedName("150180")
        public Integer c150180;
        @SerializedName("150181")
        public Integer c150181;
        @SerializedName("150182")
        public Integer c150182;
        @SerializedName("150183")
        public Integer c150183;
        @SerializedName("150184")
        public Integer c150184;
        @SerializedName("150185")
        public Integer c150185;
        @SerializedName("150186")
        public Integer c150186;
        @SerializedName("150187")
        public Integer c150187;
        @SerializedName("150188")
        public Integer c150188;
        @SerializedName("150189")
        public Integer c150189;
        @SerializedName("150190")
        public Integer c150190;
        @SerializedName("150191")
        public Integer c150191;
        @SerializedName("150192")
        public Integer c150192;
        @SerializedName("150193")
        public Integer c150193;
        @SerializedName("150194")
        public Integer c150194;
        @SerializedName("150195")
        public Integer c150195;
        @SerializedName("150196")
        public Integer c150196;
        @SerializedName("150197")
        public Integer c150197;
        @SerializedName("150198")
        public Integer c150198;
        @SerializedName("150199")
        public Integer c150199;
        @SerializedName("150200")
        public Integer c150200;
        @SerializedName("150201")
        public Integer c150201;
        @SerializedName("150202")
        public Integer c150202;
        @SerializedName("150203")
        public Integer c150203;
        @SerializedName("150204")
        public Integer c150204;
        @SerializedName("150205")
        public Integer c150205;
        @SerializedName("150206")
        public Integer c150206;
        @SerializedName("150207")
        public Integer c150207;
        @SerializedName("150208")
        public Integer c150208;
        @SerializedName("150209")
        public Integer c150209;
        @SerializedName("150210")
        public Integer c150210;
        @SerializedName("150211")
        public Integer c150211;
        @SerializedName("150212")
        public Integer c150212;
        @SerializedName("150213")
        public Integer c150213;
        @SerializedName("150214")
        public Integer c150214;
        @SerializedName("150215")
        public Integer c150215;
        @SerializedName("150216")
        public Integer c150216;
        @SerializedName("150217")
        public Integer c150217;
        @SerializedName("150218")
        public Integer c150218;
        @SerializedName("150219")
        public Integer c150219;
        @SerializedName("150220")
        public Integer c150220;
        @SerializedName("150221")
        public Integer c150221;
        @SerializedName("150222")
        public Integer c150222;
        @SerializedName("150223")
        public Integer c150223;
        @SerializedName("150224")
        public Integer c150224;
        @SerializedName("150225")
        public Integer c150225;
        @SerializedName("150226")
        public Integer c150226;
        @SerializedName("150227")
        public Integer c150227;
        @SerializedName("150228")
        public Integer c150228;
        @SerializedName("150229")
        public Integer c150229;
        @SerializedName("150230")
        public Integer c150230;
        @SerializedName("150231")
        public Integer c150231;
        @SerializedName("150232")
        public Integer c150232;
        @SerializedName("150233")
        public Integer c150233;
        @SerializedName("150234")
        public Integer c150234;
        @SerializedName("150235")
        public Integer c150235;
        @SerializedName("150236")
        public Integer c150236;
        @SerializedName("150237")
        public Integer c150237;
        @SerializedName("150238")
        public Integer c150238;
        @SerializedName("150239")
        public Integer c150239;
        @SerializedName("150240")
        public Integer c150240;
        @SerializedName("150241")
        public Integer c150241;
        @SerializedName("150242")
        public Integer c150242;
        @SerializedName("150243")
        public Integer c150243;
        @SerializedName("150244")
        public Integer c150244;
        @SerializedName("150245")
        public Integer c150245;
        @SerializedName("150246")
        public Integer c150246;
        @SerializedName("150247")
        public Integer c150247;
        @SerializedName("150248")
        public Integer c150248;
        @SerializedName("150249")
        public Integer c150249;
        @SerializedName("150250")
        public Integer c150250;
        @SerializedName("150251")
        public Integer c150251;
        @SerializedName("150252")
        public Integer c150252;
        @SerializedName("150253")
        public Integer c150253;
        @SerializedName("150254")
        public Integer c150254;
        @SerializedName("150255")
        public Integer c150255;
        @SerializedName("150256")
        public Integer c150256;
        @SerializedName("150257")
        public Integer c150257;
        @SerializedName("150258")
        public Integer c150258;
        @SerializedName("150259")
        public Integer c150259;
        @SerializedName("150260")
        public Integer c150260;
        @SerializedName("150261")
        public Integer c150261;
        @SerializedName("150262")
        public Integer c150262;
        @SerializedName("150263")
        public Integer c150263;
        @SerializedName("150264")
        public Integer c150264;
        @SerializedName("150265")
        public Integer c150265;
        @SerializedName("150266")
        public Integer c150266;
        @SerializedName("150267")
        public Integer c150267;
        @SerializedName("150268")
        public Integer c150268;
        @SerializedName("150269")
        public Integer c150269;
        @SerializedName("150270")
        public Integer c150270;
        @SerializedName("150271")
        public Integer c150271;
        @SerializedName("150272")
        public Integer c150272;
        @SerializedName("150273")
        public Integer c150273;
        @SerializedName("150274")
        public Integer c150274;
        @SerializedName("150275")
        public Integer c150275;
        @SerializedName("150276")
        public Integer c150276;
        @SerializedName("150277")
        public Integer c150277;
        @SerializedName("150278")
        public Integer c150278;
        @SerializedName("150279")
        public Integer c150279;
        @SerializedName("150280")
        public Integer c150280;
        @SerializedName("150281")
        public Integer c150281;
        @SerializedName("150282")
        public Integer c150282;
        @SerializedName("150283")
        public Integer c150283;
        @SerializedName("150285")
        public Integer c150285;
        @SerializedName("150286")
        public Integer c150286;
        @SerializedName("150287")
        public Integer c150287;
        @SerializedName("150288")
        public Integer c150288;
        @SerializedName("150289")
        public Integer c150289;
        @SerializedName("150290")
        public Integer c150290;
        @SerializedName("150291")
        public Integer c150291;
        @SerializedName("150292")
        public Integer c150292;
        @SerializedName("150293")
        public Integer c150293;
        @SerializedName("150294")
        public Integer c150294;
        @SerializedName("150295")
        public Integer c150295;
        @SerializedName("150296")
        public Integer c150296;
        @SerializedName("150297")
        public Integer c150297;
        @SerializedName("150298")
        public Integer c150298;
        @SerializedName("150299")
        public Integer c150299;
        @SerializedName("150300")
        public Integer c150300;
        @SerializedName("150301")
        public Integer c150301;
        @SerializedName("150302")
        public Integer c150302;
        @SerializedName("150303")
        public Integer c150303;
        @SerializedName("150304")
        public Integer c150304;
        @SerializedName("150305")
        public Integer c150305;
        @SerializedName("150306")
        public Integer c150306;
        @SerializedName("150307")
        public Integer c150307;
        @SerializedName("150308")
        public Integer c150308;
        @SerializedName("150309")
        public Integer c150309;
        @SerializedName("150310")
        public Integer c150310;
        @SerializedName("150311")
        public Integer c150311;
        @SerializedName("150312")
        public Integer c150312;
        @SerializedName("150313")
        public Integer c150313;
        @SerializedName("150314")
        public Integer c150314;
        @SerializedName("150315")
        public Integer c150315;
        @SerializedName("150316")
        public Integer c150316;
        @SerializedName("150317")
        public Integer c150317;
        @SerializedName("150318")
        public Integer c150318;
        @SerializedName("150319")
        public Integer c150319;
        @SerializedName("150320")
        public Integer c150320;
        @SerializedName("150321")
        public Integer c150321;
        @SerializedName("150322")
        public Integer c150322;
        @SerializedName("150323")
        public Integer c150323;
        @SerializedName("150324")
        public Integer c150324;
        @SerializedName("150325")
        public Integer c150325;
        @SerializedName("150326")
        public Integer c150326;
        @SerializedName("150327")
        public Integer c150327;
        @SerializedName("150328")
        public Integer c150328;
        @SerializedName("150329")
        public Integer c150329;
        @SerializedName("150330")
        public Integer c150330;
        @SerializedName("150331")
        public Integer c150331;
        @SerializedName("150332")
        public Integer c150332;
        @SerializedName("150333")
        public Integer c150333;
        @SerializedName("150334")
        public Integer c150334;
        @SerializedName("150335")
        public Integer c150335;
        @SerializedName("150336")
        public Integer c150336;
        @SerializedName("150337")
        public Integer c150337;
        @SerializedName("150338")
        public Integer c150338;
        @SerializedName("150339")
        public Integer c150339;
        @SerializedName("150340")
        public Integer c150340;
        @SerializedName("150341")
        public Integer c150341;
        @SerializedName("150342")
        public Integer c150342;
        @SerializedName("150343")
        public Integer c150343;
        @SerializedName("150344")
        public Integer c150344;
        @SerializedName("150345")
        public Integer c150345;
        @SerializedName("150346")
        public Integer c150346;
        @SerializedName("150347")
        public Integer c150347;
        @SerializedName("150348")
        public Integer c150348;
        @SerializedName("150349")
        public Integer c150349;
        @SerializedName("150350")
        public Integer c150350;
        @SerializedName("150351")
        public Integer c150351;
        @SerializedName("150352")
        public Integer c150352;
        @SerializedName("150353")
        public Integer c150353;
        @SerializedName("150354")
        public Integer c150354;
        @SerializedName("150355")
        public Integer c150355;
        @SerializedName("150356")
        public Integer c150356;
        @SerializedName("150357")
        public Integer c150357;
        @SerializedName("150358")
        public Integer c150358;
        @SerializedName("150359")
        public Integer c150359;
        @SerializedName("150360")
        public Integer c150360;
        @SerializedName("150361")
        public Integer c150361;
        @SerializedName("150362")
        public Integer c150362;
        @SerializedName("150363")
        public Integer c150363;
        @SerializedName("150364")
        public Integer c150364;
        @SerializedName("150365")
        public Integer c150365;
        @SerializedName("150366")
        public Integer c150366;
        @SerializedName("150367")
        public Integer c150367;
        @SerializedName("150369")
        public Integer c150369;
        @SerializedName("150370")
        public Integer c150370;
        @SerializedName("150371")
        public Integer c150371;
        @SerializedName("150372")
        public Integer c150372;
        @SerializedName("150373")
        public Integer c150373;
        @SerializedName("150374")
        public Integer c150374;
        @SerializedName("150375")
        public Integer c150375;
        @SerializedName("150376")
        public Integer c150376;
        @SerializedName("150377")
        public Integer c150377;
        @SerializedName("150378")
        public Integer c150378;
        @SerializedName("150379")
        public Integer c150379;
        @SerializedName("150380")
        public Integer c150380;
        @SerializedName("150381")
        public Integer c150381;
        @SerializedName("150382")
        public Integer c150382;
        @SerializedName("150383")
        public Integer c150383;
        @SerializedName("150384")
        public Integer c150384;
        @SerializedName("150385")
        public Integer c150385;
        @SerializedName("150386")
        public Integer c150386;
        @SerializedName("150387")
        public Integer c150387;
        @SerializedName("150388")
        public Integer c150388;
        @SerializedName("150389")
        public Integer c150389;
        @SerializedName("150390")
        public Integer c150390;
        @SerializedName("150391")
        public Integer c150391;
        @SerializedName("150392")
        public Integer c150392;
        @SerializedName("150393")
        public Integer c150393;
        @SerializedName("150394")
        public Integer c150394;
        @SerializedName("150395")
        public Integer c150395;
        @SerializedName("150396")
        public Integer c150396;
        @SerializedName("150397")
        public Integer c150397;
        @SerializedName("150398")
        public Integer c150398;
        @SerializedName("150399")
        public Integer c150399;
        @SerializedName("150400")
        public Integer c150400;
        @SerializedName("150401")
        public Integer c150401;
        @SerializedName("150402")
        public Integer c150402;
        @SerializedName("150403")
        public Integer c150403;
        @SerializedName("150404")
        public Integer c150404;
        @SerializedName("150405")
        public Integer c150405;
        @SerializedName("150406")
        public Integer c150406;
        @SerializedName("150407")
        public Integer c150407;
        @SerializedName("150408")
        public Integer c150408;
        @SerializedName("150409")
        public Integer c150409;
        @SerializedName("150410")
        public Integer c150410;
        @SerializedName("150411")
        public Integer c150411;
        @SerializedName("150412")
        public Integer c150412;
        @SerializedName("150413")
        public Integer c150413;
        @SerializedName("150414")
        public Integer c150414;
        @SerializedName("150415")
        public Integer c150415;
        @SerializedName("150416")
        public Integer c150416;
        @SerializedName("150417")
        public Integer c150417;
        @SerializedName("150418")
        public Integer c150418;
        @SerializedName("150420")
        public Integer c150420;
        @SerializedName("150421")
        public Integer c150421;
        @SerializedName("150422")
        public Integer c150422;
        @SerializedName("150423")
        public Integer c150423;
        @SerializedName("150424")
        public Integer c150424;
        @SerializedName("150425")
        public Integer c150425;
        @SerializedName("150426")
        public Integer c150426;
        @SerializedName("150427")
        public Integer c150427;
        @SerializedName("150428")
        public Integer c150428;
        @SerializedName("150429")
        public Integer c150429;
        @SerializedName("150430")
        public Integer c150430;
        @SerializedName("150431")
        public Integer c150431;
        @SerializedName("150432")
        public Integer c150432;
        @SerializedName("150433")
        public Integer c150433;
        @SerializedName("150434")
        public Integer c150434;
        @SerializedName("150435")
        public Integer c150435;
        @SerializedName("150436")
        public Integer c150436;
        @SerializedName("150437")
        public Integer c150437;
        @SerializedName("150438")
        public Integer c150438;
        @SerializedName("150439")
        public Integer c150439;
        @SerializedName("150440")
        public Integer c150440;
        @SerializedName("150441")
        public Integer c150441;
        @SerializedName("150442")
        public Integer c150442;
        @SerializedName("150443")
        public Integer c150443;
        @SerializedName("150444")
        public Integer c150444;
        @SerializedName("150445")
        public Integer c150445;
        @SerializedName("150446")
        public Integer c150446;
        @SerializedName("150447")
        public Integer c150447;
        @SerializedName("150448")
        public Integer c150448;
        @SerializedName("150449")
        public Integer c150449;
        @SerializedName("150450")
        public Integer c150450;
        @SerializedName("150451")
        public Integer c150451;
        @SerializedName("150452")
        public Integer c150452;
        @SerializedName("150453")
        public Integer c150453;
        @SerializedName("150454")
        public Integer c150454;
        @SerializedName("150456")
        public Integer c150456;
        @SerializedName("150457")
        public Integer c150457;
        @SerializedName("150459")
        public Integer c150459;
        @SerializedName("150460")
        public Integer c150460;
        @SerializedName("150462")
        public Integer c150462;
        @SerializedName("150463")
        public Integer c150463;
        @SerializedName("150464")
        public Integer c150464;
        @SerializedName("150465")
        public Integer c150465;
        @SerializedName("150466")
        public Integer c150466;
        @SerializedName("150467")
        public Integer c150467;
        @SerializedName("150468")
        public Integer c150468;
        @SerializedName("150469")
        public Integer c150469;
        @SerializedName("150470")
        public Integer c150470;
        @SerializedName("150471")
        public Integer c150471;
        @SerializedName("150472")
        public Integer c150472;
        @SerializedName("150473")
        public Integer c150473;
        @SerializedName("150474")
        public Integer c150474;
        @SerializedName("150476")
        public Integer c150476;
        @SerializedName("150477")
        public Integer c150477;
        @SerializedName("150478")
        public Integer c150478;
        @SerializedName("150479")
        public Integer c150479;
        @SerializedName("150480")
        public Integer c150480;
        @SerializedName("150481")
        public Integer c150481;
        @SerializedName("150482")
        public Integer c150482;
        @SerializedName("150483")
        public Integer c150483;
        @SerializedName("150484")
        public Integer c150484;
        @SerializedName("150485")
        public Integer c150485;
        @SerializedName("150486")
        public Integer c150486;
        @SerializedName("150487")
        public Integer c150487;
        @SerializedName("150488")
        public Integer c150488;
        @SerializedName("150489")
        public Integer c150489;
        @SerializedName("150491")
        public Integer c150491;
        @SerializedName("150492")
        public Integer c150492;
        @SerializedName("150493")
        public Integer c150493;
        @SerializedName("150494")
        public Integer c150494;
        @SerializedName("150495")
        public Integer c150495;
        @SerializedName("150496")
        public Integer c150496;
        @SerializedName("150497")
        public Integer c150497;
        @SerializedName("150498")
        public Integer c150498;
        @SerializedName("150499")
        public Integer c150499;
        @SerializedName("150500")
        public Integer c150500;
        @SerializedName("150501")
        public Integer c150501;
        @SerializedName("150502")
        public Integer c150502;
        @SerializedName("150503")
        public Integer c150503;
        @SerializedName("150504")
        public Integer c150504;
        @SerializedName("150505")
        public Integer c150505;
        @SerializedName("150506")
        public Integer c150506;
        @SerializedName("150507")
        public Integer c150507;
        @SerializedName("150508")
        public Integer c150508;
        @SerializedName("150509")
        public Integer c150509;
        @SerializedName("150510")
        public Integer c150510;
        @SerializedName("150511")
        public Integer c150511;
        @SerializedName("150512")
        public Integer c150512;
        @SerializedName("150513")
        public Integer c150513;
        @SerializedName("150514")
        public Integer c150514;
        @SerializedName("150515")
        public Integer c150515;
        @SerializedName("150516")
        public Integer c150516;
        @SerializedName("150517")
        public Integer c150517;
        @SerializedName("150518")
        public Integer c150518;
        @SerializedName("150519")
        public Integer c150519;
        @SerializedName("150521")
        public Integer c150521;
        @SerializedName("150522")
        public Integer c150522;
        @SerializedName("150523")
        public Integer c150523;
        @SerializedName("150524")
        public Integer c150524;
        @SerializedName("150526")
        public Integer c150526;
        @SerializedName("150527")
        public Integer c150527;
        @SerializedName("300001")
        public Integer c300001;
        @SerializedName("300002")
        public Integer c300002;
        @SerializedName("300003")
        public Integer c300003;
        @SerializedName("300004")
        public Integer c300004;
        @SerializedName("300005")
        public Integer c300005;
        @SerializedName("300006")
        public Integer c300006;
        @SerializedName("300007")
        public Integer c300007;
        @SerializedName("300008")
        public Integer c300008;
        @SerializedName("300009")
        public Integer c300009;
        @SerializedName("300010")
        public Integer c300010;
        @SerializedName("300011")
        public Integer c300011;
        @SerializedName("300012")
        public Integer c300012;
        @SerializedName("300013")
        public Integer c300013;
        @SerializedName("300014")
        public Integer c300014;
        @SerializedName("300015")
        public Integer c300015;
        @SerializedName("300016")
        public Integer c300016;
        @SerializedName("300017")
        public Integer c300017;
        @SerializedName("300018")
        public Integer c300018;
        @SerializedName("300019")
        public Integer c300019;
        @SerializedName("300020")
        public Integer c300020;
        @SerializedName("300021")
        public Integer c300021;
        @SerializedName("300022")
        public Integer c300022;
        @SerializedName("300023")
        public Integer c300023;
        @SerializedName("300024")
        public Integer c300024;
        @SerializedName("300025")
        public Integer c300025;
        @SerializedName("300026")
        public Integer c300026;
        @SerializedName("300027")
        public Integer c300027;
        @SerializedName("300028")
        public Integer c300028;
        @SerializedName("300029")
        public Integer c300029;
        @SerializedName("300030")
        public Integer c300030;
        @SerializedName("300031")
        public Integer c300031;
        @SerializedName("300032")
        public Integer c300032;
        @SerializedName("300033")
        public Integer c300033;
        @SerializedName("300034")
        public Integer c300034;
        @SerializedName("300035")
        public Integer c300035;
        @SerializedName("300036")
        public Integer c300036;
        @SerializedName("300037")
        public Integer c300037;
        @SerializedName("300038")
        public Integer c300038;
        @SerializedName("300039")
        public Integer c300039;
        @SerializedName("300040")
        public Integer c300040;
        @SerializedName("300041")
        public Integer c300041;
        @SerializedName("300042")
        public Integer c300042;
        @SerializedName("300043")
        public Integer c300043;
        @SerializedName("300044")
        public Integer c300044;
        @SerializedName("300045")
        public Integer c300045;
        @SerializedName("300046")
        public Integer c300046;
        @SerializedName("300047")
        public Integer c300047;
        @SerializedName("300048")
        public Integer c300048;
        @SerializedName("300049")
        public Integer c300049;
        @SerializedName("300050")
        public Integer c300050;
        @SerializedName("300051")
        public Integer c300051;
        @SerializedName("300052")
        public Integer c300052;
        @SerializedName("300053")
        public Integer c300053;
        @SerializedName("300054")
        public Integer c300054;
        @SerializedName("300055")
        public Integer c300055;
        @SerializedName("300056")
        public Integer c300056;
        @SerializedName("300057")
        public Integer c300057;
        @SerializedName("300058")
        public Integer c300058;
        @SerializedName("300059")
        public Integer c300059;
        @SerializedName("300060")
        public Integer c300060;
        @SerializedName("300061")
        public Integer c300061;
        @SerializedName("300062")
        public Integer c300062;
        @SerializedName("300063")
        public Integer c300063;
        @SerializedName("300064")
        public Integer c300064;
        @SerializedName("300065")
        public Integer c300065;
        @SerializedName("300066")
        public Integer c300066;
        @SerializedName("300067")
        public Integer c300067;
        @SerializedName("300068")
        public Integer c300068;
        @SerializedName("300069")
        public Integer c300069;
        @SerializedName("300070")
        public Integer c300070;
        @SerializedName("300071")
        public Integer c300071;
        @SerializedName("300072")
        public Integer c300072;
        @SerializedName("300073")
        public Integer c300073;
        @SerializedName("300074")
        public Integer c300074;
        @SerializedName("300075")
        public Integer c300075;
        @SerializedName("300076")
        public Integer c300076;
        @SerializedName("300077")
        public Integer c300077;
        @SerializedName("300078")
        public Integer c300078;
        @SerializedName("300079")
        public Integer c300079;
        @SerializedName("300080")
        public Integer c300080;
        @SerializedName("300081")
        public Integer c300081;
        @SerializedName("300082")
        public Integer c300082;
        @SerializedName("300083")
        public Integer c300083;
        @SerializedName("300084")
        public Integer c300084;
        @SerializedName("300085")
        public Integer c300085;
        @SerializedName("300086")
        public Integer c300086;
        @SerializedName("300087")
        public Integer c300087;
        @SerializedName("300089")
        public Integer c300089;
        @SerializedName("300090")
        public Integer c300090;
        @SerializedName("300092")
        public Integer c300092;
        @SerializedName("300093")
        public Integer c300093;
        @SerializedName("300094")
        public Integer c300094;
        @SerializedName("300095")
        public Integer c300095;
        @SerializedName("300096")
        public Integer c300096;
        @SerializedName("300097")
        public Integer c300097;
        @SerializedName("300098")
        public Integer c300098;
        @SerializedName("300099")
        public Integer c300099;
        @SerializedName("300100")
        public Integer c300100;
        @SerializedName("300105")
        public Integer c300105;
        @SerializedName("300106")
        public Integer c300106;
        @SerializedName("300107")
        public Integer c300107;
        @SerializedName("300108")
        public Integer c300108;
        @SerializedName("300109")
        public Integer c300109;
        @SerializedName("300110")
        public Integer c300110;
        @SerializedName("300111")
        public Integer c300111;
        @SerializedName("300112")
        public Integer c300112;
        @SerializedName("300113")
        public Integer c300113;
        @SerializedName("300114")
        public Integer c300114;
        @SerializedName("300115")
        public Integer c300115;
        @SerializedName("300116")
        public Integer c300116;
        @SerializedName("300117")
        public Integer c300117;
        @SerializedName("300118")
        public Integer c300118;
        @SerializedName("300119")
        public Integer c300119;
        @SerializedName("300120")
        public Integer c300120;
        @SerializedName("300121")
        public Integer c300121;
        @SerializedName("300122")
        public Integer c300122;
        @SerializedName("300123")
        public Integer c300123;
        @SerializedName("300124")
        public Integer c300124;
        @SerializedName("300125")
        public Integer c300125;
        @SerializedName("300126")
        public Integer c300126;
        @SerializedName("300128")
        public Integer c300128;
        @SerializedName("300129")
        public Integer c300129;
        @SerializedName("300130")
        public Integer c300130;
        @SerializedName("300131")
        public Integer c300131;
        @SerializedName("300132")
        public Integer c300132;
        @SerializedName("300133")
        public Integer c300133;
        @SerializedName("300134")
        public Integer c300134;
        @SerializedName("300135")
        public Integer c300135;
        @SerializedName("300136")
        public Integer c300136;
        @SerializedName("300137")
        public Integer c300137;
        @SerializedName("300138")
        public Integer c300138;
        @SerializedName("300139")
        public Integer c300139;
        @SerializedName("300140")
        public Integer c300140;
        @SerializedName("300141")
        public Integer c300141;
        @SerializedName("300142")
        public Integer c300142;
        @SerializedName("300143")
        public Integer c300143;
        @SerializedName("300145")
        public Integer c300145;
        @SerializedName("300146")
        public Integer c300146;
        @SerializedName("300147")
        public Integer c300147;
        @SerializedName("300148")
        public Integer c300148;
        @SerializedName("300149")
        public Integer c300149;
        @SerializedName("300150")
        public Integer c300150;
        @SerializedName("300151")
        public Integer c300151;
        @SerializedName("300152")
        public Integer c300152;
        @SerializedName("300153")
        public Integer c300153;
        @SerializedName("300154")
        public Integer c300154;
        @SerializedName("300155")
        public Integer c300155;
        @SerializedName("300156")
        public Integer c300156;
        @SerializedName("300157")
        public Integer c300157;
        @SerializedName("300159")
        public Integer c300159;
        @SerializedName("300160")
        public Integer c300160;
        @SerializedName("300161")
        public Integer c300161;
        @SerializedName("300162")
        public Integer c300162;
        @SerializedName("300163")
        public Integer c300163;
        @SerializedName("300164")
        public Integer c300164;
        @SerializedName("300165")
        public Integer c300165;
        @SerializedName("300166")
        public Integer c300166;
        @SerializedName("300167")
        public Integer c300167;
        @SerializedName("300168")
        public Integer c300168;
        @SerializedName("300169")
        public Integer c300169;
        @SerializedName("300170")
        public Integer c300170;
        @SerializedName("300171")
        public Integer c300171;
        @SerializedName("300172")
        public Integer c300172;
        @SerializedName("300173")
        public Integer c300173;
        @SerializedName("300174")
        public Integer c300174;
        @SerializedName("300175")
        public Integer c300175;
        @SerializedName("300176")
        public Integer c300176;
        @SerializedName("300177")
        public Integer c300177;
        @SerializedName("300178")
        public Integer c300178;
        @SerializedName("300179")
        public Integer c300179;
        @SerializedName("300180")
        public Integer c300180;
        @SerializedName("300181")
        public Integer c300181;
        @SerializedName("300182")
        public Integer c300182;
        @SerializedName("300183")
        public Integer c300183;
        @SerializedName("300184")
        public Integer c300184;
        @SerializedName("300185")
        public Integer c300185;
        @SerializedName("300186")
        public Integer c300186;
        @SerializedName("300187")
        public Integer c300187;
        @SerializedName("300188")
        public Integer c300188;
        @SerializedName("300189")
        public Integer c300189;
        @SerializedName("300190")
        public Integer c300190;
        @SerializedName("300191")
        public Integer c300191;
        @SerializedName("300192")
        public Integer c300192;
        @SerializedName("300193")
        public Integer c300193;
        @SerializedName("300194")
        public Integer c300194;
        @SerializedName("300195")
        public Integer c300195;
        @SerializedName("300196")
        public Integer c300196;
        @SerializedName("300197")
        public Integer c300197;
        @SerializedName("300199")
        public Integer c300199;
        @SerializedName("300201")
        public Integer c300201;
        @SerializedName("300202")
        public Integer c300202;
        @SerializedName("300203")
        public Integer c300203;
        @SerializedName("300204")
        public Integer c300204;
        @SerializedName("300205")
        public Integer c300205;
        @SerializedName("300207")
        public Integer c300207;
        @SerializedName("300208")
        public Integer c300208;
        @SerializedName("300209")
        public Integer c300209;
        @SerializedName("300210")
        public Integer c300210;
        @SerializedName("300211")
        public Integer c300211;
        @SerializedName("300212")
        public Integer c300212;
        @SerializedName("300213")
        public Integer c300213;
        @SerializedName("300214")
        public Integer c300214;
        @SerializedName("300215")
        public Integer c300215;
        @SerializedName("300216")
        public Integer c300216;
        @SerializedName("300217")
        public Integer c300217;
        @SerializedName("300218")
        public Integer c300218;
        @SerializedName("300219")
        public Integer c300219;
        @SerializedName("300220")
        public Integer c300220;
        @SerializedName("300221")
        public Integer c300221;
        @SerializedName("300222")
        public Integer c300222;
        @SerializedName("300223")
        public Integer c300223;
        @SerializedName("300224")
        public Integer c300224;
        @SerializedName("300225")
        public Integer c300225;
        @SerializedName("300226")
        public Integer c300226;
        @SerializedName("300227")
        public Integer c300227;
        @SerializedName("300228")
        public Integer c300228;
        @SerializedName("300229")
        public Integer c300229;
        @SerializedName("300230")
        public Integer c300230;
        @SerializedName("300231")
        public Integer c300231;
        @SerializedName("300232")
        public Integer c300232;
        @SerializedName("300233")
        public Integer c300233;
        @SerializedName("300234")
        public Integer c300234;
        @SerializedName("300235")
        public Integer c300235;
        @SerializedName("300236")
        public Integer c300236;
        @SerializedName("300237")
        public Integer c300237;
        @SerializedName("300238")
        public Integer c300238;
        @SerializedName("300239")
        public Integer c300239;
        @SerializedName("300240")
        public Integer c300240;
        @SerializedName("300241")
        public Integer c300241;
        @SerializedName("300242")
        public Integer c300242;
        @SerializedName("300243")
        public Integer c300243;
        @SerializedName("300244")
        public Integer c300244;
        @SerializedName("300245")
        public Integer c300245;
        @SerializedName("300246")
        public Integer c300246;
        @SerializedName("300247")
        public Integer c300247;
        @SerializedName("300248")
        public Integer c300248;
        @SerializedName("300249")
        public Integer c300249;
        @SerializedName("300251")
        public Integer c300251;
        @SerializedName("300252")
        public Integer c300252;
        @SerializedName("300253")
        public Integer c300253;
        @SerializedName("300254")
        public Integer c300254;
        @SerializedName("300255")
        public Integer c300255;
        @SerializedName("300256")
        public Integer c300256;
        @SerializedName("300260")
        public Integer c300260;
        @SerializedName("300261")
        public Integer c300261;
        @SerializedName("300262")
        public Integer c300262;
        @SerializedName("300263")
        public Integer c300263;
        @SerializedName("300264")
        public Integer c300264;
        @SerializedName("300265")
        public Integer c300265;
        @SerializedName("300266")
        public Integer c300266;
        @SerializedName("300267")
        public Integer c300267;
        @SerializedName("300268")
        public Integer c300268;
        @SerializedName("300269")
        public Integer c300269;
        @SerializedName("300270")
        public Integer c300270;
        @SerializedName("300271")
        public Integer c300271;
        @SerializedName("300272")
        public Integer c300272;
        @SerializedName("300273")
        public Integer c300273;
        @SerializedName("300274")
        public Integer c300274;
        @SerializedName("300275")
        public Integer c300275;
        @SerializedName("300276")
        public Integer c300276;
        @SerializedName("300277")
        public Integer c300277;
        @SerializedName("300278")
        public Integer c300278;
        @SerializedName("300279")
        public Integer c300279;
        @SerializedName("300280")
        public Integer c300280;
        @SerializedName("300281")
        public Integer c300281;
        @SerializedName("300282")
        public Integer c300282;
        @SerializedName("300283")
        public Integer c300283;
        @SerializedName("300284")
        public Integer c300284;
        @SerializedName("300285")
        public Integer c300285;
        @SerializedName("300286")
        public Integer c300286;
        @SerializedName("300287")
        public Integer c300287;
        @SerializedName("300288")
        public Integer c300288;
        @SerializedName("300289")
        public Integer c300289;
        @SerializedName("300291")
        public Integer c300291;
        @SerializedName("300292")
        public Integer c300292;
        @SerializedName("300293")
        public Integer c300293;
        @SerializedName("300294")
        public Integer c300294;
        @SerializedName("300295")
        public Integer c300295;
        @SerializedName("300296")
        public Integer c300296;
        @SerializedName("300297")
        public Integer c300297;
        @SerializedName("300298")
        public Integer c300298;
        @SerializedName("300299")
        public Integer c300299;
        @SerializedName("300300")
        public Integer c300300;
        @SerializedName("300301")
        public Integer c300301;
        @SerializedName("300302")
        public Integer c300302;
        @SerializedName("300304")
        public Integer c300304;
        @SerializedName("300305")
        public Integer c300305;
        @SerializedName("300306")
        public Integer c300306;
        @SerializedName("300307")
        public Integer c300307;
        @SerializedName("300308")
        public Integer c300308;
        @SerializedName("300309")
        public Integer c300309;
        @SerializedName("300310")
        public Integer c300310;
        @SerializedName("300311")
        public Integer c300311;
        @SerializedName("300312")
        public Integer c300312;
        @SerializedName("300313")
        public Integer c300313;
        @SerializedName("300314")
        public Integer c300314;
        @SerializedName("300315")
        public Integer c300315;
        @SerializedName("300316")
        public Integer c300316;
        @SerializedName("300317")
        public Integer c300317;
        @SerializedName("300318")
        public Integer c300318;
        @SerializedName("300319")
        public Integer c300319;
        @SerializedName("300321")
        public Integer c300321;
        @SerializedName("300322")
        public Integer c300322;
        @SerializedName("100000")
        public Integer c100000;
        @SerializedName("100001")
        public Integer c100001;
        @SerializedName("100002")
        public Integer c100002;
        @SerializedName("100003")
        public Integer c100003;
        @SerializedName("100005")
        public Integer c100005;
        @SerializedName("100006")
        public Integer c100006;
        @SerializedName("100008")
        public Integer c100008;
        @SerializedName("100009")
        public Integer c100009;
        @SerializedName("100010")
        public Integer c100010;
        @SerializedName("100011")
        public Integer c100011;
        @SerializedName("100012")
        public Integer c100012;
        @SerializedName("100013")
        public Integer c100013;
        @SerializedName("100014")
        public Integer c100014;
        @SerializedName("100015")
        public Integer c100015;
        @SerializedName("100016")
        public Integer c100016;
        @SerializedName("100017")
        public Integer c100017;
        @SerializedName("100018")
        public Integer c100018;
        @SerializedName("100019")
        public Integer c100019;
        @SerializedName("100020")
        public Integer c100020;
        @SerializedName("100021")
        public Integer c100021;
        @SerializedName("100022")
        public Integer c100022;
        @SerializedName("100023")
        public Integer c100023;
        @SerializedName("100024")
        public Integer c100024;
        @SerializedName("100025")
        public Integer c100025;
        @SerializedName("100026")
        public Integer c100026;
        @SerializedName("100027")
        public Integer c100027;
        @SerializedName("100028")
        public Integer c100028;
        @SerializedName("100029")
        public Integer c100029;
        @SerializedName("100031")
        public Integer c100031;
        @SerializedName("100032")
        public Integer c100032;
        @SerializedName("100033")
        public Integer c100033;
        @SerializedName("100034")
        public Integer c100034;
        @SerializedName("100036")
        public Integer c100036;
        @SerializedName("100037")
        public Integer c100037;
        @SerializedName("100039")
        public Integer c100039;
        @SerializedName("100040")
        public Integer c100040;
        @SerializedName("100041")
        public Integer c100041;
        @SerializedName("100042")
        public Integer c100042;
        @SerializedName("100043")
        public Integer c100043;
        @SerializedName("100044")
        public Integer c100044;
        @SerializedName("100045")
        public Integer c100045;
        @SerializedName("100046")
        public Integer c100046;
        @SerializedName("100047")
        public Integer c100047;
        @SerializedName("100048")
        public Integer c100048;
        @SerializedName("100049")
        public Integer c100049;
        @SerializedName("100050")
        public Integer c100050;
        @SerializedName("100051")
        public Integer c100051;
        @SerializedName("100052")
        public Integer c100052;
        @SerializedName("100053")
        public Integer c100053;
        @SerializedName("100054")
        public Integer c100054;
        @SerializedName("100055")
        public Integer c100055;
        @SerializedName("100056")
        public Integer c100056;
        @SerializedName("100058")
        public Integer c100058;
        @SerializedName("100060")
        public Integer c100060;
        @SerializedName("100061")
        public Integer c100061;
        @SerializedName("100062")
        public Integer c100062;
        @SerializedName("100063")
        public Integer c100063;
        @SerializedName("100064")
        public Integer c100064;
        @SerializedName("100065")
        public Integer c100065;
        @SerializedName("100066")
        public Integer c100066;
        @SerializedName("100067")
        public Integer c100067;
        @SerializedName("100068")
        public Integer c100068;
        @SerializedName("100069")
        public Integer c100069;
        @SerializedName("100071")
        public Integer c100071;
        @SerializedName("100072")
        public Integer c100072;
        @SerializedName("100073")
        public Integer c100073;
        @SerializedName("100074")
        public Integer c100074;
        @SerializedName("10001")
        public Integer c10001;
        @SerializedName("10002")
        public Integer c10002;
        @SerializedName("10003")
        public Integer c10003;
        @SerializedName("10004")
        public Integer c10004;
        @SerializedName("10005")
        public Integer c10005;
        @SerializedName("10006")
        public Integer c10006;
        @SerializedName("10007")
        public Integer c10007;
        @SerializedName("10008")
        public Integer c10008;
        @SerializedName("10009")
        public Integer c10009;
        @SerializedName("10010")
        public Integer c10010;
        @SerializedName("10011")
        public Integer c10011;
        @SerializedName("10012")
        public Integer c10012;
        @SerializedName("10013")
        public Integer c10013;
        @SerializedName("10014")
        public Integer c10014;
        @SerializedName("10015")
        public Integer c10015;
        @SerializedName("10016")
        public Integer c10016;
        @SerializedName("10017")
        public Integer c10017;
        @SerializedName("10018")
        public Integer c10018;
        @SerializedName("10019")
        public Integer c10019;
        @SerializedName("10020")
        public Integer c10020;
        @SerializedName("10021")
        public Integer c10021;
        @SerializedName("10022")
        public Integer c10022;
        @SerializedName("10023")
        public Integer c10023;
        @SerializedName("10025")
        public Integer c10025;
        @SerializedName("10026")
        public Integer c10026;
        @SerializedName("10027")
        public Integer c10027;
        @SerializedName("10028")
        public Integer c10028;
        @SerializedName("10029")
        public Integer c10029;
        @SerializedName("10030")
        public Integer c10030;
        @SerializedName("10031")
        public Integer c10031;
        @SerializedName("10035")
        public Integer c10035;
        @SerializedName("10038")
        public Integer c10038;
        @SerializedName("10039")
        public Integer c10039;
        @SerializedName("10040")
        public Integer c10040;
        @SerializedName("10041")
        public Integer c10041;
        @SerializedName("10043")
        public Integer c10043;
        @SerializedName("10045")
        public Integer c10045;

        public String getTitleId() {
            return titleId;
        }

        public String getPictureID() {
            return pictureId;
        }
    }

    public class Data {

        private String position;

        @SerializedName("type")
        private String type;
        @SerializedName("id")
        private String id;
        @SerializedName("attributes")
        private Attributes attributes = new Attributes();
        @SerializedName("relationships")
        private Relationships relationships;
        @SerializedName("links")
        private Links links;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public Relationships getRelationships() {
            return relationships;
        }

        public void setRelationships(Relationships relationships) {
            this.relationships = relationships;
        }

        public Links getLinks() {
            return links;
        }

        public void setLinks(Links links) {
            this.links = links;
        }

        public int getChampionWins(String champion) {
            return MappingUtils.getPlayerStatsValue("CharacterWins", champion, getAttributes().getStats());
        }

        public int getChampionLosses(String champion) {
            return MappingUtils.getPlayerStatsValue("CharacterLosses", champion, getAttributes().getStats());
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }

    public class Links {

        @SerializedName("schema")
        private String schema;
        @SerializedName("self")
        private String self;

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

    }

    public class Relationships {

        @SerializedName("assets")
        private Assets assets;

        public Assets getAssets() {
            return assets;
        }

        public void setAssets(Assets assets) {
            this.assets = assets;
        }

    }

}