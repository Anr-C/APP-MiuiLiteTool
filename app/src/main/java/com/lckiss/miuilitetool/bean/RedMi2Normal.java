package com.lckiss.miuilitetool.bean;

/**
 * Created by lckiss on 17-11-2.
 */

public class RedMi2Normal implements BaseMobile {

    private String mode = "红米Note2官方版";

    private String app = "AnalyticsCore,AntiSpam,AppIndexProvider,AtciService,AutoDialer,BSPTelephonyDevTool,BasicDreams,BatteryWarning,Bluetooth,BrowserProviderProxy,BugReport,Calculator,CalendarImporter,CaptivePortalLogin,CertInstaller,CloudService,DeskClock,DocumentsUI,DrmProvider,Email,EngineerMode,EngineerModeSim,FM,FTPreCheck,FileExplorer,Galaxy4,GameCenter,GuardProvider,HTMLViewer,HoloSpiralWallpaper,HybridAccessory,HybridPlatform,Joyose,KSICibaEngine,KeyChain,LiveWallpapers,LiveWallpapersPicker,LocationEM2,MTKAndroidSuiteDaemon,MTKLogger,MTKThermalManager,MetokNLP,MiAssistant,MiDrive,MiLinkService,MiLivetalk,MiWallpaper,Mipay,MiuiBluetooth,MiuiCompass,MiuiContentCatcher,MiuiDaemon,MiuiScreenRecorder,MiuiSuperMarket,MiuiVideo,MiuiVpnSdkManager,MtkFloatMenu,MusicFX,NlpService,NoiseField,Notes,Omacp,PacProcessor,PaymentService,PersonalAssistantPlugin,PhaseBeam,PhotoTable,PicoTts,PowerKeeper,PrintSpooler,Provision,SSCMService,SYSOPT,SecurityAdd,SecurityCoreAdd,SmsExtra,SogouInput,SoundRecorder,SpacesCore,SpacesPolicyApp,SystemAdSolution,SystemBaseFunctions,SystemHelper,ThemeManager,ThemeModule,TouchAssistant,TranslationService,Updater,UserDictionaryProvider,VipAccount,VisualizationWallpapers,VoiceAssist,VoiceUnlock,WMService,WebViewGoogle,Whetstone,XMPass,XiaomiAccount,XiaomiServiceFramework,XiaomiSimActivateService,XiaomiVip,YGPS,cit,jjhome,mab,miui,miuisystem";
    private String priApp = "AuthManager,Backup,BackupRestoreConfirmation,Browser,CDS_INFO,Calendar,CalendarProvider,CleanMaster,CloudBackup,CloudServiceSysbase,Contacts,ContactsProvider,ContentExtension,DefaultContainerService,DownloadProvider,DownloadProviderUi,ExternalStorageProvider,FindDevice,FusedLocation,InCallUI,InputDevices,ManagedProvisioning,MediaProvider,MiDrop,MiGameCenterSDKService,MiWebView,MiuiCamera,MiuiGallery,MiuiHome,MiuiKeyguard,MiuiSystemUI,Mms,MmsService,Music,OneTimeInitializer,PackageInstaller,PersonalAssistant,ProxyHandler,QuickSearchBox,SecurityCenter,Settings,SettingsProvider,SharedStorageBackup,Shell,SpacesManagerService,Stk1,TeleService,Telecom,TelephonyProvider,VirtualSim,VpnDialogs,WallpaperCropper,Weather,WeatherProvider,YellowPage";

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