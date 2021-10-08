/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MasterdataConfigComponentsPage, MasterdataConfigDeleteDialog, MasterdataConfigUpdatePage } from './masterdata-config.page-object';

const expect = chai.expect;

describe('MasterdataConfig e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let masterdataConfigUpdatePage: MasterdataConfigUpdatePage;
  let masterdataConfigComponentsPage: MasterdataConfigComponentsPage;
  let masterdataConfigDeleteDialog: MasterdataConfigDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MasterdataConfigs', async () => {
    await navBarPage.goToEntity('masterdata-config');
    masterdataConfigComponentsPage = new MasterdataConfigComponentsPage();
    await browser.wait(ec.visibilityOf(masterdataConfigComponentsPage.title), 5000);
    expect(await masterdataConfigComponentsPage.getTitle()).to.eq('jhipsterApp.masterdataConfig.home.title');
  });

  it('should load create MasterdataConfig page', async () => {
    await masterdataConfigComponentsPage.clickOnCreateButton();
    masterdataConfigUpdatePage = new MasterdataConfigUpdatePage();
    expect(await masterdataConfigUpdatePage.getPageTitle()).to.eq('jhipsterApp.masterdataConfig.home.createOrEditLabel');
    await masterdataConfigUpdatePage.cancel();
  });

  it('should create and save MasterdataConfigs', async () => {
    const nbButtonsBeforeCreate = await masterdataConfigComponentsPage.countDeleteButtons();

    await masterdataConfigComponentsPage.clickOnCreateButton();
    await promise.all([
      masterdataConfigUpdatePage.setNameInput('name'),
      masterdataConfigUpdatePage.setPathInput('path'),
      masterdataConfigUpdatePage.setClazzInput('clazz'),
      masterdataConfigUpdatePage.setCollectionNameInput('collectionName'),
      masterdataConfigUpdatePage.setEnvironmentInput('environment'),
      masterdataConfigUpdatePage.setContentTypeInput('contentType'),
      masterdataConfigUpdatePage.setPortInput('port'),
      masterdataConfigUpdatePage.setUrlInput('url')
    ]);
    expect(await masterdataConfigUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await masterdataConfigUpdatePage.getPathInput()).to.eq('path', 'Expected Path value to be equals to path');
    expect(await masterdataConfigUpdatePage.getClazzInput()).to.eq('clazz', 'Expected Clazz value to be equals to clazz');
    expect(await masterdataConfigUpdatePage.getCollectionNameInput()).to.eq(
      'collectionName',
      'Expected CollectionName value to be equals to collectionName'
    );
    expect(await masterdataConfigUpdatePage.getEnvironmentInput()).to.eq(
      'environment',
      'Expected Environment value to be equals to environment'
    );
    expect(await masterdataConfigUpdatePage.getContentTypeInput()).to.eq(
      'contentType',
      'Expected ContentType value to be equals to contentType'
    );
    expect(await masterdataConfigUpdatePage.getPortInput()).to.eq('port', 'Expected Port value to be equals to port');
    expect(await masterdataConfigUpdatePage.getUrlInput()).to.eq('url', 'Expected Url value to be equals to url');
    await masterdataConfigUpdatePage.save();
    expect(await masterdataConfigUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await masterdataConfigComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MasterdataConfig', async () => {
    const nbButtonsBeforeDelete = await masterdataConfigComponentsPage.countDeleteButtons();
    await masterdataConfigComponentsPage.clickOnLastDeleteButton();

    masterdataConfigDeleteDialog = new MasterdataConfigDeleteDialog();
    expect(await masterdataConfigDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.masterdataConfig.delete.question');
    await masterdataConfigDeleteDialog.clickOnConfirmButton();

    expect(await masterdataConfigComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
