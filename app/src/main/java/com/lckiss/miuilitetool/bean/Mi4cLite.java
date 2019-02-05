package com.lckiss.miuilitetool.bean;

/**
 * @author lckiss
 * @date 17-10-24
 */
public class Mi4cLite implements BaseMobile {

    public String mode = "MI4C极度精简版";

    String app = "AnalyticsCore,MiPlay,ThemeManager,AntHalService,ThemeModule,AntiSpam,MiWallpaper," +
            "TimeService,TouchAssistant,AppIndexProvider,MiuiBluetooth,TranslationService,Bluetooth," +
            "UpnpService,BluetoothMidiService,MiuiCompass,MiuiContentCatcher," +
            "CABLService,MiuiDaemon,Calculator,WAPPushManager,CaptivePortalLogin,MiuiScreenRecorder," +
            "WMService,CdmaCallOptions,WallpaperBackup,CertInstaller,MiuiVpnSdkManager,Cit,ModemTestMode," +
            "WebViewGoogle,Notes,WfdService,CtsShimPrebuilt,PacProcessor,Whetstone,DMService,DeskClock," +
            "PhotoTable,XiaomiAccount,EasterEgg,PicoTts,PowerKeeper,XiaomiServiceFramework,ExtShared," +
            "PrintRecommendationService,FidoAuthen,FidoClient,QtiTelephonyService," +
            "FileExplorer,GuardProvider,SSCMService,colorservice,HTMLViewer,SYSOPT," +
            "HybridAccessory,HybridPlatform,SampleAuthenticatorService,datastatusnotification,SecurityAdd,embms," +
            "SecurityCoreAdd,fastdormancy,KSICibaEngine,SimContacts,KeyChain,SmsExtra,miui,LiveWallpapersPicker," +
            "miuisystem,MetokNLP,SpacesCore,shutdownlistener,SpacesPolicyApp,telresources,MiLinkService," +
            "uimremoteclient,MiLivetalk,SystemBaseFunctions,xdivert";

    String priApp = "AuthManager,FindDevice,Provision,Backup,FusedLocation,ProxyHandler,BackupRestoreConfirmation," +
            "InCallUI,QtiTetherService,BlockedNumberProvider,InputDevices,QuickSearchBox,ManagedProvisioning," +
            "SecureSampleAuthService,CNEService,MediaProvider,SecurityCenter,Calendar,MiDrop,Settings,CalendarProvider," +
            "SettingsProvider,CallLogBackup,SharedStorageBackup,CarrierConfig,MiuiCamera,Shell,CellBroadcastReceiver," +
            "MiuiGallery,SoundRecorder,MiuiHome,SpacesManagerService,MiuiKeyguard,StatementService," +
            "CloudServiceSysbase,MiuiPackageInstaller,TeleService,Contacts,MiuiScanner,Telecom,ContactsProvider," +
            "TelephonyProvider,ContentExtension,CtsShimPrivPrebuilt,MiuiSystemUI,DefaultContainerService,MiuiVideo," +
            "VpnDialogs,DocumentsUI,Mms,WallpaperCropper,DownloadProvider,MmsService,Weather,DownloadProviderUi," +
            "MtpDocumentsProvider,WeatherProvider,EmergencyInfo,YellowPage,ExtServices,com.qualcomm.location," +
            "ExternalStorageProvider,OneTimeInitializer,dpmserviceapp,PackageInstaller,qcrilmsgtunnel,FidoCryptoService," +
            "PersonalAssistant";

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
