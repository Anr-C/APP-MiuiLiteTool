package com.lckiss.miuilitetool.bean;

/**
 * @author lckiss
 * @date 17-10-24
 */
public class Mi5Normal implements BaseMobile{

    private String mode="MI5官方原版";

  private   String app= "AnalyticsCore,AntHalService,AntiSpam,AppIndexProvider,BasicDreams,Bluetooth," +
            "BluetoothMidiService,BookmarkProvider,btmultisim,BugReport,Calculator,CaptivePortalLogin," +
            "CertInstaller,Cit,CloudService,com.qualcomm.qti.services.secureui,CTNVItem,CtsShimPrebuilt," +
            "DeskClock,DMService,EasterEgg,embms,ExtShared,FidoAuthen,FidoClient,FileExplorer,GuardProvider," +
            "HTMLViewer,HybridPlatform" +
            ",Joyose,KeyChain,klobugreport,KSICibaEngine,LiveWallpapersPicker,mab,MetokNLP,MiDrive," +
            "MiLinkService,MiLivetalk,Mipay,MiPlay,miui,MiuiBluetooth,MiuiCompass,MiuiContentCatcher," +
            "MiuiDaemon,MiuiScreenRecorder,miuisystem,MiWallpaper,NfcNci,NFCtestSvc,Notes,PacProcessor," +
            "PaymentService,PersonalAssistantPlugin,PhotoTable,PicoTts,PowerKeeper,PrintRecommendationService," +
            "PrintSpooler,QtiTelephonyService,SecurityAdd,SecurityCoreAdd,shutdownlistener,SmsExtra," +
            "SogouInput,SpacesCore,SpacesPolicyApp,SSCMService,Stk,SYSOPT,SystemAdSolution," +
            "SystemBaseFunctions,telresources,ThemeManager,ThemeModule,TimeService,TouchAssistant," +
            "TranslationService,TSMClient,uimremoteclient,Updater,UpnpService,UPTsmService," +
            "UserDictionaryProvider,VipAccount,VoiceAssist,WallpaperBackup,WAPPushManager,WebViewGoogle," +
            "WfdService,Whetstone,WMService,XiaomiAccount,XiaomiServiceFramework,XiaomiSimActivateService," +
            "XiaomiVip,XMPass";
  private   String priApp = "AuthManager,Backup,BackupRestoreConfirmation,BlockedNumberProvider,Calendar," +
            "CalendarProvider,CallLogBackup,CarrierConfig,CellBroadcastReceiver,CleanMaster,CloudBackup," +
            "CloudServiceSysbase,CNEService,com.qualcomm.location,Contacts,ContactsProvider,ContentExtension," +
            "CtsShimPrivPrebuilt,DefaultContainerService,DocumentsUI,DownloadProvider,DownloadProviderUi" +
            ",dpmserviceapp,EmergencyInfo,ExternalStorageProvider,ExtServices,FindDevice,FusedLocation," +
            "InCallUI,InputDevices,ManagedProvisioning,MediaProvider,MiDrop,MiGameCenterSDKService," +
            "MiuiCamera,MiuiGallery,MiuiHome,MiuiKeyguard,MiuiPackageInstaller,MiuiScanner,MiuiSystemUI," +
            "MiuiVideo,MiVRFramework,MiWebView,Mms,MmsService,MtpDocumentsProvider,Music,MusicFX," +
            "OneTimeInitializer,PackageInstaller,PersonalAssistant,Provision,ProxyHandler,qcrilmsgtunnel," +
            "QtiTetherService,QuickSearchBox,SecurityCenter,Settings,SettingsProvider," +
            "SharedStorageBackup,Shell,SmartcardService,SoundRecorder,SpacesManagerService," +
            "StatementService,Tag,Telecom,TelephonyExtVodafonePack,TelephonyProvider,TeleService," +
            "VirtualSim,VpnDialogs,WallpaperCropper,Weather,WeatherProvider,YellowPage";

    @Override
    public String getApp() {
        return app;
    }

    @Override
    public String getPriApp() {
        return priApp;
    }

    @Override
    public String getMode() {
        return mode;
    }
}
